const chipBase =
  "px-3 py-1 rounded-full border text-xs cursor-pointer transition-colors";
const chipActive = "bg-blue-600 border-blue-600 text-white";
const chipInactive = "bg-slate-50 border-slate-300 text-slate-700";

export default function FilterPanel({
  regions,
  setRegions,
  genders,
  setGenders,
  minAge,
  setMinAge,
  maxAge,
  setMaxAge,
  categories,
  setCategories,
  tags,
  setTags,
  paymentMethods,
  setPaymentMethods,
  startDate,
  setStartDate,
  endDate,
  setEndDate,
  onClear,
}) {
  const toggle = (value, list, setter) =>
    setter(list.includes(value) ? list.filter((v) => v !== value) : [...list, value]);

  const handleCommaInput = (value, setter) =>
    setter(
      value
        .split(",")
        .map((v) => v.trim())
        .filter(Boolean)
    );

  return (
    <aside className="rounded-2xl border border-slate-200 bg-white p-4 shadow-sm">
      <h2 className="mb-3 text-sm font-semibold text-slate-800">Filters</h2>

      <div className="space-y-3 text-xs">
        <div>
          <p className="mb-1 font-medium">Region</p>
          <div className="flex flex-wrap gap-2">
            {["North", "South", "East", "West"].map((r) => (
              <button
                key={r}
                type="button"
                className={`${chipBase} ${
                  regions.includes(r) ? chipActive : chipInactive
                }`}
                onClick={() => toggle(r, regions, setRegions)}
              >
                {r}
              </button>
            ))}
          </div>
        </div>

        <div>
          <p className="mb-1 font-medium">Gender</p>
          <div className="flex flex-wrap gap-2">
            {["Male", "Female", "Other"].map((g) => (
              <button
                key={g}
                type="button"
                className={`${chipBase} ${
                  genders.includes(g) ? chipActive : chipInactive
                }`}
                onClick={() => toggle(g, genders, setGenders)}
              >
                {g}
              </button>
            ))}
          </div>
        </div>

        <div className="flex items-center gap-2">
          <p className="font-medium">Age</p>
          <input
            type="number"
            className="w-16 rounded-lg border border-slate-300 px-2 py-1"
            placeholder="Min"
            value={minAge ?? ""}
            onChange={(e) =>
              setMinAge(e.target.value ? Number(e.target.value) : null)
            }
          />
          <span>–</span>
          <input
            type="number"
            className="w-16 rounded-lg border border-slate-300 px-2 py-1"
            placeholder="Max"
            value={maxAge ?? ""}
            onChange={(e) =>
              setMaxAge(e.target.value ? Number(e.target.value) : null)
            }
          />
        </div>

        <div>
          <p className="mb-1 font-medium">Categories</p>
          <input
            type="text"
            className="w-full rounded-lg border border-slate-300 px-2 py-1"
            placeholder="Comma-separated"
            value={categories.join(", ")}
            onChange={(e) => handleCommaInput(e.target.value, setCategories)}
          />
        </div>

        <div>
          <p className="mb-1 font-medium">Tags</p>
          <input
            type="text"
            className="w-full rounded-lg border border-slate-300 px-2 py-1"
            placeholder="Comma-separated"
            onChange={(e) => handleCommaInput(e.target.value, setTags)}
          />
        </div>

        <div>
          <p className="mb-1 font-medium">Payment methods</p>
          <input
            type="text"
            className="w-full rounded-lg border border-slate-300 px-2 py-1"
            placeholder="Comma-separated"
            value={paymentMethods.join(", ")}
            onChange={(e) => handleCommaInput(e.target.value, setPaymentMethods)}
          />
        </div>

        <div className="flex items-center gap-2">
          <p className="font-medium">Date</p>
          <input
            type="date"
            className="rounded-lg border border-slate-300 px-2 py-1"
            value={startDate ?? ""}
            onChange={(e) => setStartDate(e.target.value || null)}
          />
          <span>–</span>
          <input
            type="date"
            className="rounded-lg border border-slate-300 px-2 py-1"
            value={endDate ?? ""}
            onChange={(e) => setEndDate(e.target.value || null)}
          />
        </div>

        <button
          type="button"
          onClick={onClear}
          className="mt-1 rounded-lg border border-slate-300 px-3 py-1 text-xs font-medium text-slate-700 hover:bg-slate-100"
        >
          Clear filters
        </button>
      </div>
    </aside>
  );
}
