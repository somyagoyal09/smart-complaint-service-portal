/* =================================
   Smart Complaint Portal
   API Integration
================================= */

const API_BASE_URL = "/api/complaints";

/* =================================
   Load Complaints
================================= */

async function loadComplaints() {
    try {
        const loggedInUser = JSON.parse(localStorage.getItem("loggedInUser"));
        const customerId = loggedInUser ? loggedInUser.id : null;

        const url = customerId
            ? `${API_BASE_URL}/customer/${customerId}`
            : API_BASE_URL;

        const token = localStorage.getItem("token");

        const response = await fetch(url, {
            headers: {
                "Authorization": "Bearer " + token
            }
        });

        if (!response.ok) {
            throw new Error("Unable to fetch complaints.");
        }

        const complaints = await response.json();

        displayComplaints(complaints);
        updateStatistics(complaints);

    } catch (error) {
        console.error("Error loading complaints:", error);
        showMessage("Unable to load complaints. Please try again.", "error");
    }
}

/* =================================
   Display Complaints
================================= */

function displayComplaints(complaints) {
    const tableBody = document.getElementById("complaintTableBody");

    if (!tableBody) {
        return;
    }

    tableBody.innerHTML = "";

    if (complaints.length === 0) {
        tableBody.innerHTML = `
            <tr>
                <td colspan="5" class="empty-state">
                    <h3>No complaints found</h3>
                    <p>You have not submitted any complaints yet.</p>
                </td>
            </tr>
        `;
        return;
    }

    complaints.forEach(complaint => {

        const row = document.createElement("tr");

        row.innerHTML = `
            <td>#${complaint.id}</td>
            <td>${escapeHtml(complaint.title || "Untitled")}</td>
            <td>${escapeHtml(complaint.category || "General")}</td>
            <td>
                <span class="status-badge ${getStatusClass(complaint.status)}">
                    ${formatStatus(complaint.status)}
                </span>
            </td>
            <td>
                ${formatDate(complaint.createdAt)}
            </td>
        `;

        tableBody.appendChild(row);
    });
}

/* =================================
   Submit Complaint
================================= */

async function submitComplaint(event) {
    event.preventDefault();

  const title = document.getElementById("title")?.value.trim();
  const description = document.getElementById("description")?.value.trim();
  const category = document.getElementById("category")?.value;
  const priority = document.getElementById("priority")?.value;

  if (!title || !description) {
      showMessage("Please complete all required fields.", "error");
      return;
  }

    const loggedInUser = JSON.parse(localStorage.getItem("loggedInUser"));

    const complaintData = {
        title: title,
        description: description,
        category: category || null,
        priority: priority || null,
        status: "OPEN",
        customer: loggedInUser ? { id: loggedInUser.id } : null
    };

    try {
        const token = localStorage.getItem("token");

        const response = await fetch(API_BASE_URL, {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                "Authorization": "Bearer " + token
            },
            body: JSON.stringify(complaintData)
        });

        if (!response.ok) {
            throw new Error("Failed to submit complaint.");
        }

        const result = await response.json();

        showMessage(
            `Complaint submitted successfully. Complaint ID: #${result.id}`,
            "success"
        );

        document.getElementById("complaintForm").reset();

        await loadComplaints();

    } catch (error) {
        console.error("Error submitting complaint:", error);

        showMessage(
            "Unable to submit the complaint. Please try again.",
            "error"
        );
    }
}

/* =================================
   Update Dashboard Statistics
================================= */

function updateStatistics(complaints) {

    const totalElement = document.getElementById("totalComplaints");
    const openElement = document.getElementById("openComplaints");
    const resolvedElement = document.getElementById("resolvedComplaints");

    const total = complaints.length;

    const open = complaints.filter(
        complaint => complaint.status === "OPEN"
    ).length;

    const resolved = complaints.filter(
        complaint =>
            complaint.status === "RESOLVED" ||
            complaint.status === "CLOSED"
    ).length;

    if (totalElement) {
        totalElement.textContent = total;
    }

    if (openElement) {
        openElement.textContent = open;
    }

    if (resolvedElement) {
        resolvedElement.textContent = resolved;
    }
}

/* =================================
   Status Styling
================================= */

function getStatusClass(status) {

    switch (status) {

        case "OPEN":
            return "status-open";

        case "IN_PROGRESS":
            return "status-progress";

        case "RESOLVED":
            return "status-resolved";

        case "CLOSED":
            return "status-closed";

        default:
            return "status-closed";
    }
}

/* =================================
   Format Status
================================= */

function formatStatus(status) {

    if (!status) {
        return "Unknown";
    }

    return status
        .replaceAll("_", " ")
        .toLowerCase()
        .replace(/\b\w/g, letter => letter.toUpperCase());
}

/* =================================
   Format Date
================================= */

function formatDate(dateValue) {

    if (!dateValue) {
        return "N/A";
    }

    const date = new Date(dateValue);

    if (Number.isNaN(date.getTime())) {
        return "N/A";
    }

    return date.toLocaleDateString("en-IN", {
        day: "2-digit",
        month: "short",
        year: "numeric"
    });
}

/* =================================
   Display Messages
================================= */

function showMessage(message, type) {

    const messageElement = document.getElementById("message");

    if (!messageElement) {
        return;
    }

    messageElement.textContent = message;
    messageElement.className = `message ${type}`;

    setTimeout(() => {
        messageElement.className = "message";
        messageElement.textContent = "";
    }, 4000);
}

/* =================================
   Security Helper
================================= */

function escapeHtml(value) {

    const div = document.createElement("div");
    div.textContent = value;

    return div.innerHTML;
}

/* =================================
   Event Listeners
================================= */

document.addEventListener("DOMContentLoaded", () => {

    const complaintForm = document.getElementById("complaintForm");

    if (complaintForm) {
        complaintForm.addEventListener(
            "submit",
            submitComplaint
        );
    }
const loggedInUser = JSON.parse(
    localStorage.getItem("loggedInUser")
);

if (loggedInUser) {

    const profileName = document.getElementById("profileName");
    const profileEmail = document.getElementById("profileEmail");
    const profilePhone = document.getElementById("profilePhone");
    const profileRole = document.getElementById("profileRole");

    if (profileName) {
        profileName.value = loggedInUser.name || "";
    }

    if (profileEmail) {
        profileEmail.value = loggedInUser.email || "";
    }

    if (profilePhone) {
        profilePhone.value = loggedInUser.phone || "Not provided";
    }

    if (profileRole) {
        profileRole.value = loggedInUser.role || "CUSTOMER";
    }
}
    loadComplaints();
    const logoutButton = document.getElementById("logoutButton");

    if (logoutButton) {
        logoutButton.addEventListener("click", () => {

            localStorage.removeItem("loggedInUser");

            window.location.href = "login.html";
        });
    }
});