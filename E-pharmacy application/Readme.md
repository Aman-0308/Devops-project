# EPharmacy Application

## Project Overview
EPharmacy is a web-based application providing a user-friendly platform for users to search and buy medicines online. The project is built from scratch with a complete Kubernetes environment setup and follows a microservice architecture where multiple services collaborate to offer seamless experiences—from user registration to real-time order tracking.

## Major Modules
- **User Registration and Login**
- **Medicine Catalogue Management**
- **Shopping Cart Management**
- **Payment Gateway Integration**
- **Order Tracking and Management**

## Architecture
The EPharmacy application leverages **Microservice Architecture** to ensure robustness, scalability, and maintainability. Each microservice is responsible for a specific task, communicating with other services via REST APIs.

### Microservices
- **Customer Microservice:** Handles user authentication and profile management (registration, authentication, profile updates).
- **Medicine Microservice:** Manages the medicine inventory (retrieval of information, stock updates).
- **Cart Microservice:** Manages shopping cart operations (add, remove, update items).
- **Payment Microservice:** Integrates with payment gateways to process user payments.
- **Order Microservice:** Manages order placement, tracking, and information retrieval.
- **Gateway Service:**
  - Entry point for all microservice requests
  - Handles routing, load balancing, and traffic management via Spring Cloud Gateway
  - Uses Spring Cloud Consul for centralized configuration, service discovery, and registration

## Technology Stack
- **Backend:** Spring Boot, Spring Data, Spring REST, Spring Cloud Consul
- **Frontend:** Angular, HTML5, CSS3, Bootstrap
- **Database:** MySQL
- **DevOps:** Kubernetes (full environment setup from scratch)

## Getting Started

### Clone the repository
```bash
git clone https://github.com/Aman-0308/Devops-project.git
```

### Navigate to the E-pharmacy application directory
```bash
cd Devops-project/E-pharmacy
```

### Follow the setup instructions for each microservice
See respective folders and deployment manifests.

### For deploying with Helm, use the guide below:

## Step-by-Step Helm Install Guide (from Local Charts)
Follow these instructions to deploy the EPharmacy application's microservices using Helm charts from your local repository.

1. **Navigate to the Helm repository root**
    ```bash
    cd Helm-Repo
    ```
2. **Install each Helm chart manually**
    ```bash
    # Install auth-service
    helm install auth-service epharmacy-backend-chart/auth-service \
      --namespace epharmacy-backend \
      --create-namespace

    # Install backend-service
    helm install backend-service epharmacy-backend-chart/backend-service \
      --namespace epharmacy-backend

    # Install frontend
    helm install frontend epharmacy-frontend-chart \
      --namespace epharmacy-frontend \
      --create-namespace

    # Install observability
    helm install observability observability-chart \
      --namespace monitoring \
      --create-namespace

    # Install system config
    helm install system-config system-config-chart \
      --namespace epharmacy-config \
      --create-namespace
    ```
3. **Verify all deployments and services**
    ```bash
    kubectl get all -A
    ```
    This command will display pods, services, and deployments across all namespaces.

## Contributing
Contributions are welcome! Please fork the repository and submit a pull request.

## License
This project is licensed under the MIT License.
