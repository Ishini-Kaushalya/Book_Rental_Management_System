import React from 'react'

/**
 * Simple rental history list.
 * - Shows who rented what and whether it was returned.
 */
export default function RentalHistory({ rentals }) {
  return (
    <div className="card">
      <h3 className="text-lg font-semibold mb-4">Rental History</h3>
      <div className="space-y-3">
        {rentals.map(r => (
          <div key={r.id} className="flex items-center justify-between border-b border-gray-100 pb-2">
            <div className="text-sm">
              <div className="font-medium">{r.userName} <span className="text-gray-500">({r.userEmail})</span></div>
              <div className="text-gray-600">Book ID: {r.book?.id}</div>
            </div>
            <span className={`badge ${r.returnDate ? 'badge-green' : 'badge-red'}`}>
              {r.returnDate ? 'Returned' : 'Ongoing'}
            </span>
          </div>
        ))}
        {rentals.length === 0 && <div className="text-sm text-gray-500">No rentals yet.</div>}
      </div>
    </div>
  )
}
