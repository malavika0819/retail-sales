export default function PaginationControls({
  page,
  totalPages,
  hasNext,
  hasPrevious,
  onChange,
}) {
  return (
    <div className="mt-3 flex items-center justify-center gap-3 text-xs">
      <button
        type="button"
        disabled={!hasPrevious}
        onClick={() => onChange(page - 1)}
        className="rounded-lg border border-slate-300 px-3 py-1 disabled:opacity-40"
      >
        Prev
      </button>
      <span className="text-slate-600">
        Page {page + 1} of {totalPages || 1}
      </span>
      <button
        type="button"
        disabled={!hasNext}
        onClick={() => onChange(page + 1)}
        className="rounded-lg border border-slate-300 px-3 py-1 disabled:opacity-40"
      >
        Next
      </button>
    </div>
  );
}
