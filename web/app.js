const API_URL = "http://localhost:8080/weather-backend/weather";

let weatherData = [];
let filteredData = [];

async function loadWeatherData() {
    try {
        const response = await fetch(API_URL);

        if (!response.ok) {
            throw new Error("Backend not reachable");
        }

        weatherData = await response.json();
        filteredData = [...weatherData];
        renderDashboard();

    } catch (error) {
        alert("❌ Backend not reachable");
        console.error(error);
    }
}

function createWeatherCard(city) {
    return `
        <div class="card">
            <div class="rank-badge">${city.rank}</div>

            <h2>${city.city}</h2>
            <p>${city.description}</p>

            <div class="temperature">${city.temp}°C</div>

            <div class="comfort-bar">
                <div class="comfort-fill" 
                     style="width:${city.comfort}%; background:limegreen;">
                </div>
            </div>

            <p>Comfort: ${city.comfort}</p>
        </div>
    `;
}

function renderDashboard() {
    const grid = document.getElementById("weatherGrid");

    if (filteredData.length === 0) {
        grid.innerHTML = "<p>No results found</p>";
        return;
    }

    grid.innerHTML = filteredData.map(createWeatherCard).join("");
}

document.getElementById("searchInput").addEventListener("input", e => {
    const value = e.target.value.toLowerCase();

    filteredData = weatherData.filter(city =>
        city.city.toLowerCase().includes(value) ||
        city.description.toLowerCase().includes(value)
    );

    renderDashboard();
});

document.getElementById("themeToggle").addEventListener("click", () => {
    document.body.classList.toggle("dark-mode");
    document.body.classList.toggle("light-mode");
});

loadWeatherData();
