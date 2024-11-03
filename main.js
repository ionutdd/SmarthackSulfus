// Set dimensions for the SVG overlay
const width = 800;  // Match mapContainer width
const height = 600; // Match mapContainer height

// D3.js graph drawing example
const svg = d3.select("#overlay")
    .attr("width", width)
    .attr("height", height);

// Define sample data for refineries, tanks, and customers with their coordinates
const locations = {
    refineries: [{ id: "Refinery 1", x: 100, y: 100 }],
    tanks: [{ id: "Tank 1", x: 300, y: 200 }],
    customers: [{ id: "Customer 1", x: 500, y: 400 }],
};

// Draw circles for refineries, tanks, and customers
svg.selectAll("circle.refinery")
    .data(locations.refineries)
    .enter()
    .append("circle")
    .attr("class", "refinery")
    .attr("cx", d => d.x)
    .attr("cy", d => d.y)
    .attr("r", 8)
    .attr("fill", "red")
    .append("title")
    .text(d => d.id); // Tooltip with ID

svg.selectAll("circle.tank")
    .data(locations.tanks)
    .enter()
    .append("circle")
    .attr("class", "tank")
    .attr("cx", d => d.x)
    .attr("cy", d => d.y)
    .attr("r", 8)
    .attr("fill", "blue")
    .append("title")
    .text(d => d.id); // Tooltip with ID

svg.selectAll("circle.customer")
    .data(locations.customers)
    .enter()
    .append("circle")
    .attr("class", "customer")
    .attr("cx", d => d.x)
    .attr("cy", d => d.y)
    .attr("r", 8)
    .attr("fill", "green")
    .append("title")
    .text(d => d.id); // Tooltip with ID

// Draw lines representing routes (example data, replace with your own routes)
const routes = [
    { source: locations.refineries[0], target: locations.tanks[0] },
    { source: locations.tanks[0], target: locations.customers[0] },
];

svg.selectAll("line.route")
    .data(routes)
    .enter()
    .append("line")
    .attr("class", "route")
    .attr("x1", d => d.source.x)
    .attr("y1", d => d.source.y)
    .attr("x2", d => d.target.x)
    .attr("y2", d => d.target.y)
    .attr("stroke", "black")
    .attr("stroke-width", 2);
