FROM node:18-alpine AS BUILD
WORKDIR /app

# Copy package.json and package-lock.json as dependencies
COPY package.json ./
RUN npm install 

# Copy the rest of the application code
COPY angular.json ./
COPY tsconfig.json ./
COPY tsconfig.app.json ./
COPY tsconfig.spec.json ./
COPY src ./src
COPY karma.conf.js ./
COPY .browserlistrc ./

# Build the app for production
RUN npm install
RUN ng build --prod


# Stage 2: Final image (Alpine- Lighter version (reduce image size) to serve the app
FROM nginx:alpine AS final

# The final stage takes the built static files from the build stage and copies them into the appropriate directory for Nginx to serve them.

# Copy the build artifacts from the build stage
COPY --from=BUILD /app/dist /usr/share/nginx/html

# COPY --from=build /app/dist /usr/share/nginx/html copies the built files from the build container (the build stage) into Nginx’s default directory for serving HTML files (/usr/share/nginx/html).

ENTRYPOINT ["/usr/sbin/nginx"]

# Start the application
CMD ["nginx", "-g", "daemon off;"]
