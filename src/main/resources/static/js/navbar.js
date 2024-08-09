// navbar.js

// Function to fetch user roles
function fetchUserRoles() {
    return fetch('/api/user/roles', {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
        }
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .catch(error => {
            console.error('Error fetching user roles:', error);
            return []; // Return an empty array in case of error
        });
}

// Function to generate and display navigation bar based on roles
function displayNavbar(roles) {
    const navbar = document.getElementById('navbar');

    // Define the options and the roles that can access them
    const options = [
        {text: 'Listings', url: '/listings', roles: ['ROLE_CLIENT', 'ROLE_BROKER', 'ROLE_ADMIN']},
        {text: 'Add Listings', url: '/listings/add', roles: ['ROLE_BROKER']},
        // {text: 'Manage Listings', url: `/listings/broker/${brokerId}`, roles: ['ROLE_BROKER']},
        {text: 'Admin Page', url: '/admin', roles: ['ROLE_ADMIN']},
        {text: 'Add Unassigned Listing', url: '/admin/add/listing', roles: ['ROLE_ADMIN']},
        {text: 'Unassigned Listings', url: '/broker/unassigned', roles: ['ROLE_BROKER']}
    ];

    // Clear existing items
    navbar.innerHTML = '';

    // Generate the navigation items based on roles
    options.forEach(option => {
        if (roles.some(role => option.roles.includes(role))) {
            const li = document.createElement('li');
            const a = document.createElement('a');
            a.href = option.url;
            a.textContent = option.text;
            li.appendChild(a);
            navbar.appendChild(li);
        }
    });
}

// Add event listener to the logout link
document.addEventListener('DOMContentLoaded', function () {
    var logoutLink = document.getElementById('logoutLink');

    if (logoutLink) {
        logoutLink.addEventListener('click', function (event) {
            event.preventDefault(); // Prevent the default link behavior

            // Perform the logout operation
            fetch('/logout', {
                method: 'POST',
                credentials: 'same-origin', // Include cookies with the request
            })
                .then(response => {
                    if (response.ok) {
                        // Redirect to login page or homepage
                        window.location.href = '/login'; // Adjust as needed
                    } else {
                        // Handle logout failure (optional)
                        console.error('Logout failed');
                    }
                })
                .catch(error => {
                    console.error('Error during logout:', error);
                });
        });
    }
});