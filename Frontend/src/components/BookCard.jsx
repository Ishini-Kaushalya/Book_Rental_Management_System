import React from "react";

/**
 * Displays a single book with an availability badge and a Rent button.
 * - If today is past the returnDate, the book becomes available again.
 */
export default function BookCard({ book, onRent }) {
  const today = new Date();
  const isReturned = book.returnDate
    ? new Date(book.returnDate) < today
    : false;
  const canRent = book.available || isReturned;

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
        <span className={`badge ${canRent ? "badge-green" : "badge-red"}`}>
          {canRent ? "Available" : "Rented"}
        </span>
      </div>

      <div className="mt-4 flex gap-2">
        <button
          disabled={!canRent}
          onClick={onRent}
          className={`btn ${
            canRent ? "btn-primary" : "opacity-50 cursor-not-allowed"
          }`}
          title={canRent ? "Rent this book" : "Currently not available"}
        >
          Rent
        </button>
      </div>
    </div>
  );
}
