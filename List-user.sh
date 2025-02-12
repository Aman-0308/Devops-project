#!/bin/bash

# GitHub API URL
API_URL="https://api.github.com"

# GitHub credentials from environment variables
USERNAME="$USERNAME"
TOKEN="$TOKEN"

# User and Repository information from command-line arguments
REPO_OWNER="$1"
REPO_NAME="$2"

# Check for required credentials
if [[ -z "$USERNAME" || -z "$TOKEN" ]]; then
  echo "Error: GitHub USERNAME or TOKEN environment variables are not set."
  exit 1
fi

# Function to make a GET request to the GitHub API
function github_api_get {
    local endpoint="$1"
    local url="${API_URL}/${endpoint}"

    # Send a GET request to the GitHub API with authentication
    response=$(curl -s -u "${USERNAME}:${TOKEN}" "$url")
}

# Function to list users with read access to the repository
function list_users_with_read_access {
    local endpoint="repos/${REPO_OWNER}/${REPO_NAME}/collaborators"

    # Fetch the list of collaborators on the repository
    collaborators=$(github_api_get "$endpoint" | jq -r '.[] | select(.permissions.pull == true) | .login')

    # Display the list of collaborators
    if [[ -z "$collaborators" ]]; then
        echo "No collaborators found for ${REPO_OWNER}/${REPO_NAME}."
    else
        echo "Collaborators for ${REPO_OWNER}/${REPO_NAME}:"
        echo "$collaborators"
    fi
}

# Main script execution
echo "Listing collaborators for ${REPO_OWNER}/${REPO_NAME}..."
list_users_with_read_access
