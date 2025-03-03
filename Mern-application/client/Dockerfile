# Stage 1: Build base 
FROM node:16 AS build
WORKDIR /app

# Copy package.json and package-lock.json as dependencies
COPY package*.json ./
RUN npm install

# Copy the rest of the client source code
COPY . . 

# Move environment file
RUN mv .env.example .env

# Build the React app for production
RUN npm run build

# Stage 3: Final image (Alpine- Lighter version (reduce image size) to serve the app
FROM nginx:alpine AS final

# The final stage takes the built static files from the build stage and copies them into the appropriate directory for Nginx to serve them.

# Copy the build artifacts from the build stage
COPY --from=build /app/dist /usr/share/nginx/html

# COPY --from=build /app/dist /usr/share/nginx/html copies the built files from the build container (the build stage) into Nginx’s default directory for serving HTML files (/usr/share/nginx/html).

# Expose the port the app will run on
EXPOSE 8080

# Start the application
CMD ["nginx", "-g", "daemon off;"]

