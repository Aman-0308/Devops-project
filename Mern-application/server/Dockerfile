# Stage 1: Build backend image
FROM node:16.20.2-buster-slim AS build

# Set the working directory inside the container
WORKDIR /app

# Copy the package.json and package-lock.json files to the container
COPY package*.json ./

# Install dependencies
RUN npm install

# Copy the rest of the server code to the container
COPY . .

# Expose the server port
EXPOSE 3030

# Set environment variables (optional, can be overridden via Docker Compose)
ENV MONGO_URI=mongodb://mongo:27017/mern_ecommerce

# Start the server
CMD ["npm", "start"]

