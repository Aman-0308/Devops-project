Script Breakdown and Detailed Explanation:
1. Shebang Line:
bash
#!/bin/bash
•	Purpose: Specifies the script should be run using the Bash shell.
2. Defining Constants and Variables:
bash
# GitHub API URL
API_URL="https://api.github.com"
•	Purpose: Sets the base URL for GitHub's API endpoints.
bash
# GitHub username and personal access token
USERNAME=$username
TOKEN=$token
•	Purpose: Sets the USERNAME and TOKEN variables with values from previously defined environment variables. Note: This is only valid if $username and $token are set in the environment or passed from another source.
3. Command-Line Arguments:
bash
# User and Repository information
REPO_OWNER=$1
REPO_NAME=$2
Purpose: Assigns the first and second command-line arguments to REPO_OWNER and REPO_NAME.
Usage: This allows you to specify the repository owner and repository name when you run the script.
Example Usage: ./script_name.sh owner_name repository_name
4. Defining the github_api_get Function:
bash
function github_api_get {
    local endpoint="$1"
    local url="${API_URL}/${endpoint}"

    # Send a GET request to the GitHub API with authentication
    curl -s -u "${USERNAME}:${TOKEN}" "$url"
}
Purpose: Defines a function named github_api_get to make authenticated GET requests to the GitHub API.
Parameters:
endpoint: The specific API endpoint to request.
Function Body:
Constructs the full URL by appending the endpoint to the base API URL.
Uses curl to send a GET request with silent mode (-s) and basic authentication (-u "${USERNAME}:${TOKEN}").
5. Defining the list_users_with_read_access Function:
bash
function list_users_with_read_access {
    local endpoint="repos/${REPO_OWNER}/${REPO_NAME}/collaborators"

    # Fetch the list of collaborators on the repository
    collaborators="$(github_api_get "$endpoint" | jq -r '.[] | select(.permissions.pull == true) | .login')"

    # Display the list of collaborators with read access
    if [[ -z "$collaborators" ]]; then
        echo "No users with read access found for ${REPO_OWNER}/${REPO_NAME}."
    else
        echo "Users with read access to ${REPO_OWNER}/${REPO_NAME}:"
        echo "$collaborators"
    fi
}
Purpose: Defines a function named list_users_with_read_access to list users with read access to a specified repository.
Function Body:
•	Constructs the endpoint URL for fetching collaborators.
•	Calls the github_api_get function to fetch data from the GitHub API.
•	Pipes the JSON response to jq for processing:
•	.[]: Iterates over each item in the JSON array.
•	select(.permissions.pull == true): Filters items where the user has read (pull) permission.
•	.login: Extracts the username (login) field from the filtered items.
•	Stores the resulting usernames in the collaborators variable.
•	Checks if any collaborators were found and prints the appropriate message.

6. Main Script Execution:
bash
# Main script
echo "Listing users with read access to ${REPO_OWNER}/${REPO_NAME} ..."
list_users_with_read_access
Purpose: This section is where the script starts its main execution after defining variables and functions.
Steps:
Prints a message indicating the action being performed.
Calls the list_users_with_read_access function to list collaborators with read access.
Execution of Shell Script 

Detailed Execution Order:
•	Shebang Line: Specifies the interpreter.
•	Variable Definitions: Sets up initial variables.
•	Command-Line Arguments: Assigns values from command-line arguments.
•	Function Definitions: Defines github_api_get and list_users_with_read_access functions but does not execute them yet.
Main Script Execution:

# Main script
echo "Listing users with read access to ${REPO_OWNER}/${REPO_NAME} ..."
list_users_with_read_access


Prints a message using echo.
•	Calls list_users_with_read_access:

**Command**:  local endpoint="repos/${REPO_OWNER}/${REPO_NAME}/collaborators"
This line defines a local variable called endpoint and assigns it a URL path as a string.
repos/: This is a static part of the URL indicating that we are accessing the repositories endpoint of the GitHub API.
${REPO_OWNER}: This is a variable placeholder that will be replaced with the value of the REPO_OWNER variable. It represents the owner of the repository.
${REPO_NAME}: This is another variable placeholder that will be replaced with the value of the REPO_NAME variable. It represents the name of the repository.
/collaborators: This is a static part of the URL indicating that we are accessing the collaborators endpoint for the specified repository

**Command**:  collaborators="$(github_api_get "$endpoint" | jq -r '.[] | select(.permissions.pull == true) | .login')"

Calling the Function:
github_api_get "$endpoint": This part calls the github_api_get function with the endpoint as its argument.The endpoint variable holds the URL path for the GitHub API request (e.g., repos/octocat/Hello-World/collaborators).

Capturing the Output:
The output of github_api_get (the response from the GitHub API) is captured and piped (|) to jq. Inside this function, it calls github_api_get to fetch data from the GitHub API.
Filters and prints the list of users with read access.
•	jq: A command-line JSON processor used to parse and filter JSON data.
•	.[]: Iterates over each item in the JSON array.
•	select(.permissions.pull == true): Filters items where the pull permission is true, meaning the user has read access.
•	.login: Extracts the login field (the username) from the filtered items.
•	The resulting usernames are assigned to the collaborators variable.



