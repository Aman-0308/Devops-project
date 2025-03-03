# Stage 1: Install root dependencies
FROM node:16 AS build
WORKDIR /app

# Copy package.json and package-lock.json for root dependencies
COPY package*.json ./
RUN npm install

# Copy the rest of the client source code
COPY . . 

# Move environment file if needed
RUN mv .env.example .env

# Build the React app for production
RUN npm run build

# Stage 3: Final image to serve the app
FROM nginx:alpine AS final

# Copy the build artifacts from the build stage
COPY --from=build /app/dist /usr/share/nginx/html

# Expose the port the app will run on
EXPOSE 8080

# Start the application
CMD ["nginx", "-g", "daemon off;"]

