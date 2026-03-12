
var view = new ol.View({
  projection: 'EPSG:4326',
  center: [ 78.40560096569789, 27.159972766243083],
  zoom: 16,
});

// Define the map view
var base_maps = new ol.layer.Group({
  'title': 'Base maps',
  layers: [
      new ol.layer.Tile({
          title: 'OSM',
          type: 'base',
          visible: true,
          source: new ol.source.OSM()
      }),
      new ol.layer.Tile({
          title: 'CartoDB Positron',
          type: 'base',
          visible: false,
          source: new ol.source.XYZ({
              url: 'https://{1-4}.basemaps.cartocdn.com/light_all/{z}/{x}/{y}.png',
              attributions: '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors &copy; <a href="https://carto.com/attributions">CARTO</a>'
          })
      }),
      new ol.layer.Tile({
          source: new ol.source.TileJSON({
              attributions: '@MapTiler',
              url: 'https://api.maptiler.com/maps/toner-v2/tiles.json?key=iVy8qXSX5hN7aJhQp2Na'
          }),
          title: 'Toner',
          type: 'base',
          visible: false
      }),

      new ol.layer.Tile({
          source: new ol.source.TileJSON({
              attributions: '@MapTiler',
              url: 'https://api.maptiler.com/maps/topo-v2/tiles.json?key=iVy8qXSX5hN7aJhQp2Na'
          }),
          title: 'Topo',
          type: 'base',
          visible: true,
          maxZoom: 23
      }),

      new ol.layer.Tile({
          title: 'Satellite',
          type: 'base',
          visible: false,
          source: new ol.source.XYZ({
              //  attributions: ['Powered by Esri',
              //      'Source: Esri, DigitalGlobe, GeoEye, Earthstar Geographics, CNES/Airbus DS, USDA, USGS, AeroGRID, IGN, and the GIS User Community'
              //  ],
              attributionsCollapsible: false,
              url: 'https://services.arcgisonline.com/ArcGIS/rest/services/World_Imagery/MapServer/tile/{z}/{y}/{x}',
              maxZoom: 23
          })
      }),
  ]
});

var OSM = new ol.layer.Tile({
  source: new ol.source.OSM(),
  type: 'base',
  title: 'OSM',
});

var overlays = new ol.layer.Group({
  'title': 'Overlays',
  layers: []
});

map = new ol.Map({
  target: 'map',
  view: view,
  // overlays: [overlay]
});

map.addLayer(base_maps);
map.addLayer(overlays);

var currentLayer = null;

function removeCurrentLayer() {
  if (currentLayer) {  // Check if there's a current layer on the map
      map.removeLayer(currentLayer);  // Remove the current layer from the map
      currentLayer = null;  // Reset the currentLayer variable
  }
}

layerSwitcher = new ol.control.LayerSwitcher({
  activationMode: 'click',
  startActive: false,
  tipLabel: 'Layers', // Optional label for button
  groupSelectStyle: 'children', // Can be 'children' [default], 'group' or 'none'
  collapseTipLabel: 'Collapse layers',
});
map.addControl(layerSwitcher);

layerSwitcher.renderPanel();

District_Boundary = new ol.layer.Image({
  title: 'House Points',
  //     extent: [-180, -90, -180, 90],
  source: new ol.source.ImageWMS({
      url: 'http://localhost:8080/geoserver/PostgreSQL/wms?',
      params: {
          'LAYERS': 'PostgreSQL:w03_house_data_new_updated',
      },
      ratio: 1,
      serverType: 'geoserver'
  })
});

overlays.getLayers().push(District_Boundary);

roads= new ol.layer.Image({
  title: 'Roads ',
  //     extent: [-180, -90, -180, 90],
  source: new ol.source.ImageWMS({
      url: 'http://localhost:8080/geoserver/PostgreSQL/wms?',
      params: {
          'LAYERS': 'PostgreSQL:W03_roads',
      },
      ratio: 1,
      serverType: 'geoserver'
  })
});

overlays.getLayers().push(roads);

house_polygon = new ol.layer.Image({
  title: 'House Polygon',
  //     extent: [-180, -90, -180, 90],
  source: new ol.source.ImageWMS({
      url: 'http://localhost:8080/geoserver/PostgreSQL/wms?',
      params: {
          'LAYERS': 'PostgreSQL:Parcel_polygon_new',
      },
      ratio: 1,
      serverType: 'geoserver'
  })
});

overlays.getLayers().push(house_polygon);

// ========================= GLOBAL VARIABLES =========================
let allGarbageData = [];
let currentPage = 1;
const recordsPerPage = 10;
     // Number of records to show per batch

// ====================== TAB SWITCHING FUNCTION ======================
function showTab(tabId) {
  const contents = document.querySelectorAll('.tab-content');
  contents.forEach(content => content.style.display = 'none');
  document.getElementById(tabId).style.display = 'block';
}


// ====================== FETCH DATA FROM SERVER =====================
console.log("table-body:", document.getElementById("table-body"));

async function fetchDynamicData() {
  try {
    const response = await fetch("http://192.168.107.74:8083/Ferozabad/getZ1Type", {
      method: "POST",
      headers: {
        "Content-Type": "application/x-www-form-urlencoded"
      },
      body: "type=Z1"  // 🔁 Replace Z1 with dynamic value if needed
    });

    const data = await response.json();

    if (data && Array.isArray(data.data)) {
      allGarbageData = data.data;
      currentDisplayIndex = 0;
      document.getElementById("table-body").innerHTML = ""; // Reset table
     currentPage = 1;
     renderPaginatedTable(currentPage);

    //  updateMapMarkers(allGarbageData);  // Update map
    } else {
      console.error("Invalid data format:", data);
    }
  } catch (error) {
    console.error("Error fetching data:", error);
  }
}


// =================== RENDER TABLE IN pages  ========================
function renderPaginatedTable(page = 1) {
  const tableBody = document.getElementById("table-body");
  tableBody.innerHTML = "";

  const start = (page - 1) * recordsPerPage;
  const end = start + recordsPerPage;

  const paginatedData = allGarbageData.slice(start, end);

  if (paginatedData.length === 0) {
    tableBody.innerHTML = `<tr><td colspan="7">No data found</td></tr>`;
    return;
  }

  paginatedData.forEach((stop, i) => {
    const row = document.createElement("tr");
    row.innerHTML = `
      <td>${start + i + 1}</td>
      <td>${stop.house_id || "-"}</td>
      <td>${stop.resident_name || "-"}</td>
      <td>${stop.resident_address || "-"}</td>
      <td>${stop.updated_at ? new Date(stop.updated_at).toLocaleString() : "-"}</td>
      <td>${stop.status || "-"}</td>
      <td><button class="view-btn" data-id="${stop.collection_id}">View</button></td>
    `;
    tableBody.appendChild(row);
  });

  renderPaginationControls();
}

function renderPaginationControls() {
  const pagination = document.getElementById("pagination");
  pagination.innerHTML = "";

  const totalPages = Math.ceil(allGarbageData.length / recordsPerPage);
  if (totalPages <= 1) return;

  const createLink = (label, pageNum, isDisabled = false, isActive = false) => {
    const span = document.createElement("span");
    span.textContent = label;
    span.style.cursor = isDisabled ? "default" : "pointer";
    span.style.margin = "0 6px";
    span.style.color = isActive ? "#3c7217" : (isDisabled ? "#aaa" : "#333");
    span.style.fontWeight = isActive ? "bold" : "normal";
    if (!isDisabled && !isActive) {
      span.addEventListener("click", () => {
        currentPage = pageNum;
        renderPaginatedTable(currentPage);
      });
    }
    pagination.appendChild(span);
  };

  createLink("« Prev", currentPage - 1, currentPage === 1);

  for (let i = 1; i <= totalPages; i++) {
    createLink(i.toString(), i, false, i === currentPage);
  }

  createLink("Next »", currentPage + 1, currentPage === totalPages);
}


// ============ FILTER & RENDER DATA BY DATE =============
function filterDataByDate(selectedDate) {
  const tableBody = document.getElementById("table-body");
  tableBody.innerHTML = ""; // Clear previous

  const filteredData = allGarbageData.filter(stop => {
    if (!stop.updated_at) return false;
   const recordDate = new Date(stop.updated_at).toISOString().split('T')[0];
    // const d = new Date(stop.updated_at);
    // const recordDate =
    // d.getFullYear() + "-" +
    // String(d.getMonth() + 1).padStart(2, "0") + "-" +
    // String(d.getDate()).padStart(2, "0");
    return recordDate === selectedDate;
  });


  if (filteredData.length === 0) {
    tableBody.innerHTML = `<tr><td colspan="7">No data for this date</td></tr>`;
    return;
  }

  filteredData.forEach((stop, i) => {
    const row = document.createElement("tr");
    row.innerHTML = `
      <td>${i + 1}</td>
      <td>${stop.house_id || "-"}</td>
      <td>${stop.resident_name || "-"}</td>
      <td>${stop.resident_address || "-"}</td>
      <td>${stop.updated_at ? new Date(stop.updated_at).toLocaleString() : "-"}</td>
      <td>${stop.status || "-"}</td>
      <td><button class="view-btn" data-id="${stop.collection_id}">View</button></td>
    `;
    tableBody.appendChild(row);
  });
}

document.addEventListener("DOMContentLoaded", function () {
  const dateInput = document.getElementById("date-picker");
  const today = new Date().toISOString().split('T')[0];
  dateInput.value = today;
});

document.getElementById("date-picker").addEventListener("change", function () {
  const selectedDate = this.value;
  filterDataByDate(selectedDate);
});


// ===================== MAP MARKERS FOR GARBAGE ======================
/* function updateMapMarkers(data) {
  const vectorSource = new ol.source.Vector();

  data.forEach(stop => {
    const feature = new ol.Feature({
      geometry: new ol.geom.Point(ol.proj.fromLonLat([stop.longitude, stop.latitude])),
      name: stop.house_no
    });

    vectorSource.addFeature(feature);
  });

  const vectorLayer = new ol.layer.Vector({
    source: vectorSource,
    style: new ol.style.Style({
      image: new ol.style.Circle({
        radius: 6,
        fill: new ol.style.Fill({ color: 'red' }),
        stroke: new ol.style.Stroke({ color: 'white', width: 2 })
      })
    })
  });

  map.addLayer(vectorLayer);
}
*/
// ====================== VIEW BUTTON FUNCTIONALITY ====================
document.getElementById("table-body").addEventListener("click", (event) => {
  if (event.target.classList.contains("view-btn")) {
    const stopId = event.target.getAttribute("data-id");

    // Try to find that stop directly from allGarbageData
    const selectedStop = allGarbageData.find(stop => stop.collection_id == stopId);

    if (!selectedStop) {
      alert("Stop data not found.");
      return;
    }

    // Show info box
    const infoBox = document.getElementById('info-box');
    const infoDetails = document.getElementById('info-details');
    infoDetails.innerHTML = `
      <h4>Stop Details</h4>
      <p><strong>House Number:</strong> ${selectedStop.house_id || "-"}</p>
      <p><strong>Resident:</strong> ${selectedStop.resident_name || "-"}</p>
      <p><strong>Location:</strong> ${selectedStop.resident_address || "-"}</p>
      <p><strong>Time:</strong> ${selectedStop.updated_at ? new Date(selectedStop.updated_at).toLocaleString() : "-"}</p>
      <p><strong>Status:</strong> ${selectedStop.status || "-"}</p>
      <p><strong>Phone No:</strong>  ${selectedStop.mobile || "-"}</p>
    `;
    infoBox.style.display = 'block';


  }
});
// ===================== CLOSE INFO BOX ========================
document.getElementById('close-info-btn').addEventListener('click', () => {
  document.getElementById('info-box').style.display = 'none';
});


// ================== INITIAL LOAD + INTERVAL ===================
// document.addEventListener("DOMContentLoaded", () => {
  fetchDynamicData();
// });


