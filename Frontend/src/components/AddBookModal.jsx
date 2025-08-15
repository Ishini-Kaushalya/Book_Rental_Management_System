import React, { useState } from "react";

/**
 * Modal component for adding a new book.
 * - Props:
 *   - onClose: function to close the modal
 *   - onSubmit: function to handle book submission
 * - Maintains local state for title, author, and genre
 * - Calls onSubmit with the book data (including available = true)
 */
export default function AddBookModal({ onClose, onSubmit }) {
  const [title, setTitle] = useState(""); // Track book title
  const [author, setAuthor] = useState(""); // Track author
  const [genre, setGenre] = useState(""); // Track genre

  // Form submission handler
  const handleSubmit = (e) => {
    e.preventDefault(); // Prevent page reload
    onSubmit({ title, author, genre, available: true }); // Pass data to parent
  };

  return (
    <div className="fixed inset-0 bg-black/30 grid place-items-center p-4">
      <div className="card max-w-lg w-full">
        {/* Modal header */}
        <div className="flex items-center justify-between">
          <h3 className="text-lg font-semibold">Add a new book</h3>
          <button className="btn" onClick={onClose}>
            ✕
          </button>
        </div>

        {/* Form */}
        <form onSubmit={handleSubmit} className="mt-4 space-y-4">
          <div>
            <label className="label">Title</label>
            <input
              className="input"
              value={title}
              onChange={(e) => setTitle(e.target.value)}
              required
            />
          </div>
          <div>
            <label className="label">Author</label>
            <input
              className="input"
              value={author}
              onChange={(e) => setAuthor(e.target.value)}
              required
            />
          </div>
          <div>
            <label className="label">Genre</label>
            <input
              className="input"
              value={genre}
              onChange={(e) => setGenre(e.target.value)}
              placeholder="e.g., Fiction"
            />
          </div>

          {/* Action buttons */}
          <div className="flex justify-end gap-2">
            <button type="button" className="btn" onClick={onClose}>
              Cancel
            </button>
            <button type="submit" className="btn btn-primary">
              Save
            </button>
          </div>
        </form>
      </div>
    </div>
  );
}
