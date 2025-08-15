import React, { useEffect, useState } from "react";
import Navbar from "../components/Navbar";
import BookCard from "../components/BookCard";
import AddBookModal from "../components/AddBookModal";
import RentBookModal from "../components/RentBookModal";
import RentalHistory from "../components/RentalHistory";
import api from "../api";

/**
 * Main App page:
 * - Fetches books and rentals from backend
 * - Displays cards for books with availability badges
 * - Provides modals to add a book and rent a book
 */
export default function App() {
  const [books, setBooks] = useState([]);
  const [rentals, setRentals] = useState([]);
  const [showAddModal, setShowAddModal] = useState(false);
  const [rentTarget, setRentTarget] = useState(null);

  const fetchAll = async () => {
    const [b, r] = await Promise.all([
      api.get("/api/books"),
      api.get("/api/rentals"),
    ]);
    setBooks(b.data);
    setRentals(r.data);
  };

  useEffect(() => {
    fetchAll();
  }, []);

  const handleAddBook = async (payload) => {
    await api.post("/api/books", payload);
    setShowAddModal(false);
    await fetchAll();
  };

  const handleRentBook = async (payload) => {
    await api.post("/api/rentals", payload);
    setRentTarget(null);
    await fetchAll();
  };

  return (
    <div>
      <Navbar onAdd={() => setShowAddModal(true)} />

      <div className="max-w-6xl mx-auto p-6">
        <h1 className="text-2xl font-bold mb-4">Library — Explore & Rent</h1>
        <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-6">
          {books.map((b) => (
            <BookCard key={b.id} book={b} onRent={() => setRentTarget(b)} />
          ))}
        </div>

        <div className="mt-10">
          <RentalHistory rentals={rentals} />
        </div>
      </div>

      {showAddModal && (
        <AddBookModal
          onClose={() => setShowAddModal(false)}
          onSubmit={handleAddBook}
        />
      )}

      {rentTarget && (
        <RentBookModal
          book={rentTarget}
          onClose={() => setRentTarget(null)}
          onSubmit={handleRentBook}
        />
      )}
    </div>
  );
}
