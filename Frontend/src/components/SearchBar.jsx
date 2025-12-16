export default function SearchBar({ value, onChange }) {
  return (
    <input
      type="text"
      className="w-full max-w-md rounded-xl border border-slate-300 bg-white px-4 py-2 text-sm shadow-sm focus:border-blue-500 focus:outline-none focus:ring-2 focus:ring-blue-200"
      placeholder="Search by customer name or phone..."
      value={value}
      onChange={(e) => onChange(e.target.value)}
    />
  );
}
