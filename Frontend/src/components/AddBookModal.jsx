import React, { useState } from "react";

/**
 * Modal for adding a new book.
 * - Collects title, author, genre
 * - Defaults to "available = true"
 */
export default function AddBookModal({ onClose, onSubmit }) {
  const [title, setTitle] = useState("");
  const [author, setAuthor] = useState("");
  const [genre, setGenre] = useState("");

  const handleSubmit = (e) => {
    e.preventDefault();
    onSubmit({ title, author, genre, available: true });
  };

  return (
    <div className="fixed inset-0 bg-black/30 grid place-items-center p-4">
      <div className="card max-w-lg w-full">
        <div className="flex items-center justify-between">
          <h3 className="text-lg font-semibold">Add a new book</h3>
          <button className="btn" onClick={onClose}>
            ✕
          </button>
        </div>

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
