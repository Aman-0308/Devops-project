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

# Check for required arguments
if [[ -z "$REPO_OWNER" || -z "$REPO_NAME" ]]; then
  echo "Usage: $0 <repository_owner> <repository_name>"
  exit 1
fi

# Function to make a GET request to the GitHub API
function github_api_get {
    local endpoint="$1"
    local url="${API_URL}/${endpoint}"

    # Send a GET request to the GitHub API with authentication
    response=$(curl -s -u "${USERNAME}:${TOKEN}" "$url")
    
    # Check for API rate limit exceeded or other errors
    if echo "$response" | jq -e '.message' &>/dev/null; then
        local message=$(echo "$response" | jq -r '.message')
        echo "GitHub API Error: $message"
        exit 1
    fi

    echo "$response"
}

# Function to list users with read access to the repository
function list_users_with_read_access {
    local endpoint="repos/${REPO_OWNER}/${REPO_NAME}/collaborators?per_page=100"

    # Fetch the list of collaborators on the repository
    collaborators=$(github_api_get "$endpoint" | jq -r '.[] | .login')

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
