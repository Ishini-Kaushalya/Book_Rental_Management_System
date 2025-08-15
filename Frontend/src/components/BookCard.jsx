import React from "react";

/**
 * Displays a single book with an availability badge and a Rent button.
 */
export default function BookCard({ book, onRent }) {
  return (
    <div className="card">
      <div className="flex items-start justify-between">
        <div>
          <h2 className="text-lg font-semibold">{book.title}</h2>
          <p className="text-sm text-gray-600">by {book.author}</p>
          {book.genre && (
            <p className="text-xs mt-1 text-gray-500">{book.genre}</p>
          )}
        </div>
        <span
          className={`badge ${book.available ? "badge-green" : "badge-red"}`}
        >
          {book.available ? "Available" : "Rented"}
        </span>
      </div>

      <div className="mt-4 flex gap-2">
        <button
          disabled={!book.available}
          onClick={onRent}
          className={`btn ${
            book.available ? "btn-primary" : "opacity-50 cursor-not-allowed"
          }`}
          title={book.available ? "Rent this book" : "Currently not available"}
        >
          Rent
        </button>
      </div>
    </div>
  );
}
