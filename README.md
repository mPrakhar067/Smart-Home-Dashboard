# Smart Home Dashboard

## Overview

The Smart Home Dashboard platform offers an intuitive solution for managing and monitoring smart home devices. It empowers users to control and track their devices seamlessly through a modern web application, supported by a powerful RESTful API backend built with Java 23 and Spring Boot 3. The platform accommodates diverse device types, including lighting, heating, and security systems, while providing performance metrics to evaluate device efficiency and health.
## Features

- **Device Management**: Effortlessly add, update, and manage smart devices.
- **Performance Monitoring**: Access detailed performance metrics for devices to ensure optimal functionality.
- **Pre-Configured Device Types**: The system initializes with predefined device categories like Lighting, Heating, and Security.
- **REST API Integration**: Expose comprehensive endpoints for interacting with devices and performance metrics.
- **Data Persistence**: Securely stores information in a MySQL relational database.

## Requirements

- Java 23
- Spring Boot 3 for the backend framework.
- MySQL for data persistence.

API Endpoints
-------------
### Devices

- **POST** `/api/v1/devices`: Add a new device
- **PUT** `/api/v1/devices/{id}`: Update an existing device

### Performance Indicators

- **POST** `/api/v1/performance-indicators`: Add a new performance indicator

System Architecture
------------

- **Inventory Module**: Handles all operations related to device management.
- **Analytics Module**: Manages performance metrics and related insights.
- **Shared Module**: Provides common entities and configurations, such as DeviceType definitions.
