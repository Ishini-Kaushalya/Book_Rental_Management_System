import React from "react";

export default function RentalHistory({ rentals }) {
  const today = new Date();

  const formatStatus = (returnDate) => {
    if (!returnDate) return "No return date";
    const returnDay = new Date(returnDate);
    return returnDay < today ? "Returned" : "Need to return";
  };

  return (
    <div className="card">
      <h3 className="text-lg font-semibold mb-4">Rental History</h3>
      <div className="space-y-3">
        {rentals.map((r) => (
          <div
            key={r.id}
            className="flex items-center justify-between border-b border-gray-100 pb-2"
          >
            <div className="text-sm">
              <div className="font-medium">
                {r.userName}{" "}
                <span className="text-gray-500">({r.userEmail})</span>
              </div>
              <div className="text-gray-600">
                Book Title: {r.book?.title || "Unknown"}
              </div>
            </div>
            {r.returnDate && (
              <div
                className={`text-sm ${
                  new Date(r.returnDate) < today
                    ? "text-green-600"
                    : "text-red-600"
                }`}
              >
                {formatStatus(r.returnDate)}{" "}
                {new Date(r.returnDate) >= today &&
                  `before ${new Date(r.returnDate).toLocaleDateString()}`}
              </div>
            )}
          </div>
        ))}
        {rentals.length === 0 && (
          <div className="text-sm text-gray-500">No rentals yet.</div>
        )}
      </div>
    </div>
  );
}
