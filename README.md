# Factory Simulation for Special Metal Plates Production

---

## Project Overview
This project simulates the operation of a factory that manufactures special metal plates through a sequence of three production phases. Each phase is handled by a specific machine, and the system incorporates MQTT messaging, REST APIs, and RabbitMQ communication to manage operations effectively. The project includes:

1. **Simulation of machines for plate production**
2. **Microservices for control, product management, and sales**
3. **Client applications for order creation and machine control**

---

## Production Process
The production of a metal plate involves three phases:
1. **Shaping (M1)**
2. **Flattening (M2)**
3. **Painting (M3)**

Each machine has a unique ID and processes plates in the specified order (M1 -> M2 -> M3). The machines communicate via MQTT and log processing data. The third machine (M3) finalizes the product and reports it to the Product Microservice via a REST endpoint.

---

## System Components

### 1. **Machines**
- **Functionality:** Each machine processes a specific phase of plate production.
- **Features:**
  - Logs key events: start time, processing times for plates, and shutdown times.
  - Supports commands via Control Microservice:
    - `STOP`: Stops the machine.
    - `PAUSE`: Pauses the machine.
    - `CONT`: Resumes operation after a pause.
- Communicates with the Control Microservice and Product Microservice via MQTT.

### 2. **Control Microservice**
- Manages all machines and logs telemetry data into a database:
  - Start time, processing start and end times for each plate, stop time, pause time, and resume time.
- Sends commands (`STOP`, `PAUSE`, `CONT`) to machines.

### 3. **Product Microservice**
- Stores information about all finished plates in a database.
- Provides REST endpoints for:
  - `findAll`: Retrieve all plates.
  - `findByID`: Retrieve plate details by ID.

### 4. **Sales Microservice**
- Creates sales orders from plates managed by the Product Microservice.
- Sales orders consist of:
  - **Header:** Customer, sale date, payment date (due date), delivery address.
  - **Items:** Plate ID, price, quantity.
- Stores sales orders as `.txt` files.
- Communicates via RabbitMQ.

### 5. **Client Applications**
#### a) **Order Creation Application**
- A desktop GUI for creating sales orders.
- Retrieves data from the Product Microservice and allows users to input missing details (e.g., price, quantity, addresses).

#### b) **Control Panel Application**
- Provides a dashboard for managing machine operations via the Control Microservice.
- Displays machine statuses using icons:
  - Green: Running.
  - Yellow: Paused.
  - Red: Stopped.
- Allows sending commands (`STOP`, `PAUSE`, `CONT`) to machines.

---

## Configuration
- **Configuration:** Parameters are stored in `.properties` file for easy management.

---

## Technologies Used
- **Messaging:** MQTT, RabbitMQ
- **APIs:** REST (Spring Boot)
- **Database:** Relational database for telemetry and product data.
- **Client Applications:** JavaFX (desktop)
- **Simulation:** Java (Thread.sleep for delays)

---

## Running the Project

### Prerequisites
1. Install Java 11 or higher.
2. Set up MQTT broker and RabbitMQ server.
3. Configure databases for Control and Product Microservices.

### Steps to Run
1. **Start Microservices:**
   - Launch Control Microservice.
   - Launch Product Microservice.
   - Launch Sales Microservice.

2. **Run Machines:**
   - Start simulations for M1, M2, and M3.

3. **Launch Client Applications:**
   - Run the Order Creation Application.
   - Run the Control Panel Application.

