import React, { useState } from 'react'

/**
 * Modal for renting a selected book.
 * - Sends (bookId, userName, userEmail, userPhone) to backend
 */
export default function RentBookModal({ book, onClose, onSubmit }) {
  const [userName, setUserName] = useState('')
  const [userEmail, setUserEmail] = useState('')
  const [userPhone, setUserPhone] = useState('')

  const handleSubmit = (e) => {
    e.preventDefault();
    onSubmit({
      userName,
      userEmail,
      userPhone,
      rentalDate: new Date().toISOString().split("T")[0],
      returnDate: new Date(Date.now() + 7 * 24 * 60 * 60 * 1000)
        .toISOString()
        .split("T")[0],
      book: {
        id: book.id,
      },
    });
  };


  return (
    <div className="fixed inset-0 bg-black/30 grid place-items-center p-4">
      <div className="card max-w-lg w-full">
        <div className="flex items-center justify-between">
          <h3 className="text-lg font-semibold">Rent: {book.title}</h3>
          <button className="btn" onClick={onClose}>✕</button>
        </div>

        <form onSubmit={handleSubmit} className="mt-4 space-y-4">
          <div>
            <label className="label">Your Name</label>
            <input className="input" value={userName} onChange={e => setUserName(e.target.value)} required />
          </div>
          <div>
            <label className="label">Email</label>
            <input className="input" type="email" value={userEmail} onChange={e => setUserEmail(e.target.value)} required />
          </div>
          <div>
            <label className="label">Phone</label>
            <input className="input" value={userPhone} onChange={e => setUserPhone(e.target.value)} />
          </div>

          <div className="flex justify-end gap-2">
            <button type="button" className="btn" onClick={onClose}>Cancel</button>
            <button type="submit" className="btn btn-primary">Confirm</button>
          </div>
        </form>
      </div>
    </div>
  )
}
