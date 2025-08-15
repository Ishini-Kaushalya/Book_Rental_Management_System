import React, { useState, useEffect } from "react";
import axios from "axios";
import BookCard from "./BookCard";
import RentalHistory from "./RentalHistory";

export default function LibraryManager() {
  const [books, setBooks] = useState([]);
  const [rentals, setRentals] = useState([]);

  // Fetch initial data
  useEffect(() => {
    const fetchData = async () => {
      try {
        const [booksRes, rentalsRes] = await Promise.all([
          axios.get("/api/books"),
          axios.get("/api/rentals"),
        ]);

        // Process books with real-time availability
        const processedBooks = booksRes.data.map((book) => {
          const bookRentals = rentalsRes.data.filter(
            (r) => r.book.id === book.id
          );
          const isRented = bookRentals.some(
            (r) => new Date(r.returnDate) > new Date()
          );
          return { ...book, available: !isRented };
        });

        setBooks(processedBooks);
        setRentals(rentalsRes.data);
      } catch (error) {
        console.error("Error fetching data:", error);
      }
    };

    fetchData();

    // Set up real-time checking every minute
    const interval = setInterval(fetchData, 60000);
    return () => clearInterval(interval);
  }, []);

  // Handle renting a book
  const handleRent = async (bookId) => {
    try {
      // Your rent API call here
      await axios.post("/api/rentals", { bookId });
      // Refresh data
      const [booksRes, rentalsRes] = await Promise.all([
        axios.get("/api/books"),
        axios.get("/api/rentals"),
      ]);
      setBooks(booksRes.data);
      setRentals(rentalsRes.data);
    } catch (error) {
      console.error("Error renting book:", error);
    }
  };

  return (
    <div className="grid grid-cols-1 md:grid-cols-2 gap-8">
      <div>
        <h2 className="text-xl font-bold mb-4">Books</h2>
        <div className="space-y-4">
          {books.map((book) => (
            <BookCard
              key={book.id}
              book={book}
              onRent={() => handleRent(book.id)}
            />
          ))}
        </div>
      </div>
      <div>
        <RentalHistory rentals={rentals} />
      </div>
    </div>
  );
}
