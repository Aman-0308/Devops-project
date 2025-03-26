# Shell Scripting

## 1. Shebang Line
```bash
#!/bin/bash

## 2. Setting Variables

API_URL="https://api.github.com"
USERNAME="$USERNAME"
TOKEN="$TOKEN"
REPO_OWNER="$1"
REPO_NAME="$2"

API_URL: This variable stores the base URL for GitHub's API (https://api.github.com).
USERNAME and TOKEN: These are environment variables that are set externally (i.e., you must have set USERNAME and TOKEN in your environment before running the script).
REPO_OWNER and REPO_NAME: These variables capture the command-line arguments passed to the script when it's executed. $1 represents the first argument (REPO_OWNER), and $2 represents the second argument (REPO_NAME).


3. Checking for Required Credentials:

if [[ -z "$USERNAME" || -z "$TOKEN" ]]; then
    echo "Error: GitHub USERNAME or TOKEN environment variables are not set."
    exit 1
fi

This checks if the USERNAME or TOKEN environment variables are empty (using -z to test if the variable is null). If either USERNAME or TOKEN is not set, the script prints an error message and exits with a non-zero exit status (exit 1). If both credentials are set, the script continues executing.
 

4. Defining the Function github_api_get

function github_api_get {
    local endpoint="$1"
    local url="${API_URL}/${endpoint}"
    
    # Send a GET request to the GitHub API with authentication
    response=$(curl -s -u "${USERNAME}:${TOKEN}" "$url")
}

This function is defined to make a GET request to the GitHub API.

Parameters:
endpoint is passed as a parameter ($1) when calling the function. It specifies which part of the GitHub API to query.
url combines the API_URL with the endpoint to create the full URL for the API request.
Action:
It uses curl to send an HTTP GET request to the GitHub API.
The -s option tells curl to run silently (no progress bar).
The -u "${USERNAME}:${TOKEN}" option passes the USERNAME and TOKEN for basic authentication.
The result of the curl request (GitHub API response) is stored in the variable response.
 


5. Defining the Function list_users_with_read_access:

function list_users_with_read_access {

&nbsp;&nbsp;&nbsp; local
endpoint="repos/${REPO_OWNER}/${REPO_NAME}/collaborators"

&nbsp;

&nbsp;&nbsp;&nbsp; # Fetch
the list of collaborators on the repository

&nbsp;&nbsp;&nbsp;
collaborators=$(github_api_get "$endpoint" | jq -r '.[] |
select(.permissions.pull == true) | .login')

&nbsp;

&nbsp;&nbsp;&nbsp; # Display
the list of collaborators

&nbsp;&nbsp;&nbsp; if [[ -z "$collaborators"
]]; then

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; echo "No
collaborators found for ${REPO_OWNER}/${REPO_NAME}."

&nbsp;&nbsp;&nbsp; else

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; echo "Collaborators
for ${REPO_OWNER}/${REPO_NAME}:"

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; echo "$collaborators"

&nbsp;&nbsp;&nbsp; fi

}


 This function lists users
     who have **read access** (pull access) to a GitHub repository.
 Parameters:
 
  endpoint is constructed as "repos/${REPO_OWNER}/${REPO_NAME}/collaborators", which is the API endpoint
      for fetching collaborators on the specified repository (REPO_OWNER is the owner of the
      repository, and REPO_NAME is the name of the
      repository).
 
 Action:
 
  The function calls the
      previously defined github_api_get function to make a GET
      request to the GitHub API and fetch the collaborators list for the
      specified repository.
  The output of the GET
      request ($response) is passed through jq, which is a command-line
      JSON processor.
  
   jq -r: This option is used to
       output raw (unquoted) data from the JSON response.
   .[]: This iterates over all
       items in the JSON array (collaborators).
   select(.permissions.pull
       == true):
       Filters collaborators that have **pull** permission (read access).
   .login: Extracts the login
       (username) of the collaborator.
  
  If no collaborators are
      found with read access, it prints "No collaborators found". If collaborators are
      found, it prints their usernames.
 


6. Main Script Execution:

&nbsp;

echo "Listing collaborators for ${REPO_OWNER}/${REPO_NAME}..."list_users_with_read_access


 This line prints a message indicating that
     the script is about to list the collaborators for the specified
     repository.
 Then, it calls the list_users_with_read_access
     function to list the users with read access to the repository.





