import React from "react";

// Card component to display a book
export default function BookCard({ book, onRent }) {
  return (
    <div className="card"> {/* Card container */}
      <div className="flex items-start justify-between">
        <div>
          <h2 className="text-lg font-semibold">{book.title}</h2> {/* Book title */}
          <p className="text-sm text-gray-600">by {book.author}</p> {/* Author */}
          {book.genre && (
            <p className="text-xs mt-1 text-gray-500">{book.genre}</p> 
          )}
        </div>

        {/* Availability badge */}
        <span
          className={`badge ${book.available ? "badge-green" : "badge-red"}`}
        >
          {book.available ? "Available" : "Rented"}
        </span>
      </div>

      {/* Action button */}
      <div className="mt-4 flex gap-2">
        <button
          disabled={!book.available} // Disable if book is rented
          onClick={onRent}           // Call parent handler when renting
          className={`btn ${
            book.available ? "btn-primary" : "opacity-50 cursor-not-allowed"
          }`}
          title={book.available ? "Rent this book" : "Currently not available"} // Tooltip
        >
          Rent
        </button>
      </div>
    </div>
  );
}
