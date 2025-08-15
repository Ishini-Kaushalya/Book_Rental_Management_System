import React from "react";

// Component to display the rental history
export default function RentalHistory({ rentals }) {
  const today = new Date(); // Current date for status comparison

  // Helper function to determine rental status
  const formatStatus = (returnDate) => {
    if (!returnDate) return "No return date"; // fallback if returnDate missing
    const returnDay = new Date(returnDate);
    return returnDay < today ? "Returned" : "Need to return";
  };

  return (
    <div className="card">
      <h3 className="text-lg font-semibold mb-4">Rental History</h3>
      <div className="space-y-3">
        {/* Map through rentals and display each entry */}
        {rentals.map((r) => (
          <div
            key={r.id}
            className="flex items-center justify-between border-b border-gray-100 pb-2"
          >
            {/* Rental details */}
            <div className="text-sm">
              <div className="font-medium">{r.userName}</div>
              <div className="text-gray-600">
                Book Title: {r.book?.title || "Unknown"}
              </div>
              <div className="text-gray-500">
                Rented Date:{" "}
                {r.rentalDate
                  ? new Date(r.rentalDate).toLocaleDateString()
                  : "Unknown"}
              </div>
            </div>

            {/* Rental return status */}
            {r.returnDate && (
              <div
                className={`text-sm ${
                  new Date(r.returnDate) < today
                    ? "text-green-600" // returned
                    : "text-red-600" // still to return
                }`}
              >
                {formatStatus(r.returnDate)}{" "}
                {new Date(r.returnDate) >= today &&
                  `before ${new Date(r.returnDate).toLocaleDateString()}`}
              </div>
            )}
          </div>
        ))}

        {/* Show message if no rentals exist */}
        {rentals.length === 0 && (
          <div className="text-sm text-gray-500">No rentals yet.</div>
        )}
      </div>
    </div>
  );
}
