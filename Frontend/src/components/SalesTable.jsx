export default function SalesTable({ rows }) {
  if (!rows || rows.length === 0) {
    return (
      <div className="rounded-xl border border-dashed border-slate-300 bg-slate-50 px-4 py-6 text-center text-sm text-slate-500">
        No results found.
      </div>
    );
  }

  return (
    <div className="overflow-x-auto rounded-xl border border-slate-200 bg-white shadow-sm">
      <table className="min-w-full text-left text-xs">
        <thead className="bg-slate-100 text-[11px] uppercase tracking-wide text-slate-500">
          <tr>
            <th className="px-3 py-2">Date</th>
            <th className="px-3 py-2">Customer</th>
            <th className="px-3 py-2">Phone</th>
            <th className="px-3 py-2">Region</th>
            <th className="px-3 py-2">Product</th>
            <th className="px-3 py-2">Category</th>
            <th className="px-3 py-2">Qty</th>
            <th className="px-3 py-2">Final Amount</th>
            <th className="px-3 py-2">Payment</th>
          </tr>
        </thead>
        <tbody className="divide-y divide-slate-100 text-[11px]">
          {rows.map((r) => (
            <tr key={r.id}>
              <td className="px-3 py-2">{r.date}</td>
              <td className="px-3 py-2">{r.customerName}</td>
              <td className="px-3 py-2">{r.phoneNumber}</td>
              <td className="px-3 py-2">{r.customerRegion}</td>
              <td className="px-3 py-2">{r.productName}</td>
              <td className="px-3 py-2">{r.productCategory}</td>
              <td className="px-3 py-2">{r.quantity}</td>
              <td className="px-3 py-2">{r.finalAmount}</td>
              <td className="px-3 py-2">{r.paymentMethod}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}
