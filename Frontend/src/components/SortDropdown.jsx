export default function SortDropdown({ sortBy, setSortBy, sortDir, setSortDir }) {
  return (
    <div className="flex items-center gap-2">
      <select
        className="rounded-lg border border-slate-300 bg-white px-3 py-2 text-sm shadow-sm"
        value={sortBy}
        onChange={(e) => setSortBy(e.target.value)}
      >
        <option value="date">Date (Newest)</option>
        <option value="quantity">Quantity</option>
        <option value="customerName">Customer Name</option>
      </select>

      <select
        className="rounded-lg border border-slate-300 bg-white px-3 py-2 text-sm shadow-sm"
        value={sortDir}
        onChange={(e) => setSortDir(e.target.value)}
      >
        <option value="desc">DESC</option>
        <option value="asc">ASC</option>
      </select>
    </div>
  );
}
