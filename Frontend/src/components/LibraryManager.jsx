import React, { useEffect, useState } from "react";
import BookCard from "./BookCard";
import RentalHistory from "./RentalHistory";

export default function BookRentalSystem() {
  const [books, setBooks] = useState([]);
  const [rentals, setRentals] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  // Fetch books and rentals
  useEffect(() => {
    const fetchData = async () => {
      try {
        setLoading(true);
        // Fetch books and rentals in parallel
        const [booksResponse, rentalsResponse] = await Promise.all([
          fetch("/api/books").then((res) => res.json()),
          fetch("/api/rentals").then((res) => res.json()),
        ]);

        setBooks(booksResponse);
        setRentals(rentalsResponse);
      } catch (err) {
        setError(err.message);
        console.error("Failed to fetch data:", err);
      } finally {
        setLoading(false);
      }
    };

    fetchData();
  }, []);

  const handleRent = async (bookId) => {
    try {
      // Find the book to rent
      const bookToRent = books.find((book) => book.id === bookId);
      if (!bookToRent) {
        throw new Error("Book not found");
      }

      // Prompt user for rental information
      const userName = prompt("Enter your name:");
      const userEmail = prompt("Enter your email:");
      const userPhone = prompt("Enter your phone number:");
      const daysToRent = prompt(
        "How many days would you like to rent this book?",
        "7"
      );

      if (!userName || !userEmail || !userPhone || !daysToRent) {
        return; // User cancelled
      }

      // Calculate dates
      const rentalDate = new Date();
      const returnDate = new Date();
      returnDate.setDate(rentalDate.getDate() + parseInt(daysToRent));

      // Create rental object
      const newRental = {
        book: bookToRent,
        userName,
        userEmail,
        userPhone,
        rentalDate: rentalDate.toISOString(),
        returnDate: returnDate.toISOString(),
      };

      // Send rental request
      const response = await fetch("/api/rentals", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(newRental),
      });

      if (!response.ok) {
        throw new Error("Failed to create rental");
      }

      const createdRental = await response.json();

      // Update state
      setRentals([...rentals, createdRental]);

      // Update book availability
      setBooks(
        books.map((book) =>
          book.id === bookId ? { ...book, available: false } : book
        )
      );

      alert(
        `Successfully rented "${
          bookToRent.title
        }" until ${returnDate.toLocaleDateString()}`
      );
    } catch (err) {
      alert(`Error: ${err.message}`);
      console.error("Rental failed:", err);
    }
  };

  const refreshData = async () => {
    try {
      setLoading(true);
      const [booksResponse, rentalsResponse] = await Promise.all([
        fetch("/api/books").then((res) => res.json()),
        fetch("/api/rentals").then((res) => res.json()),
      ]);
      setBooks(booksResponse);
      setRentals(rentalsResponse);
    } catch (err) {
      setError(err.message);
    } finally {
      setLoading(false);
    }
  };

  if (loading) {
    return <div className="p-4 text-center">Loading...</div>;
  }

  if (error) {
    return (
      <div className="p-4 text-center text-red-500">
        Error: {error}
        <button
          onClick={refreshData}
          className="mt-2 px-4 py-2 bg-blue-500 text-white rounded hover:bg-blue-600"
        >
          Retry
        </button>
      </div>
    );
  }

  return (
    <div className="grid grid-cols-1 md:grid-cols-3 gap-6 p-4">
      <div className="md:col-span-2 space-y-4">
        <div className="flex justify-between items-center">
          <h2 className="text-xl font-bold">Books</h2>
          <button
            onClick={refreshData}
            className="px-3 py-1 bg-gray-200 rounded hover:bg-gray-300 text-sm"
            title="Refresh data"
          >
            Refresh
          </button>
        </div>
        <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-4">
          {books.map((book) => (
            <BookCard
              key={book.id}
              book={book}
              rentals={rentals}
              onRent={() => handleRent(book.id)}
            />
          ))}
        </div>
      </div>
      <div>
        <div className="flex justify-between items-center mb-4">
          <h2 className="text-xl font-bold">Rental History</h2>
          <button
            onClick={refreshData}
            className="px-3 py-1 bg-gray-200 rounded hover:bg-gray-300 text-sm"
            title="Refresh data"
          >
            Refresh
          </button>
        </div>
        <RentalHistory rentals={rentals} />
      </div>
    </div>
  );
}
