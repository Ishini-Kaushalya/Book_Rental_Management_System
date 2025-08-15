import React from 'react'

/**
 * Top navigation with a big call-to-action button to add a book.
 */
export default function Navbar({ onAdd }) {
  return (
    <div className="bg-white/70 backdrop-blur sticky top-0 z-10 border-b border-gray-100">
      <div className="max-w-6xl mx-auto px-6 py-4 flex items-center justify-between">
        <div className="font-bold text-lg">📚 Book Rental Management System</div>
        <button className="btn btn-primary" onClick={onAdd}>
          + Add Book
        </button>
      </div>
    </div>
  )
}
