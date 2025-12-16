import { useMemo, useState } from "react";
import SearchBar from "../components/SearchBar";
import SortDropdown from "../components/SortDropdown";
import FilterPanel from "../components/FilterPanel";
import SalesTable from "../components/SalesTable";
import PaginationControls from "../components/PaginationControls";
import { useSalesQuery } from "../hooks/useSalesQuery";

export default function Dashboard() {
  const [page, setPage] = useState(0);
  const [search, setSearch] = useState("");

  const [regions, setRegions] = useState([]);
  const [genders, setGenders] = useState([]);
  const [minAge, setMinAge] = useState(null);
  const [maxAge, setMaxAge] = useState(null);
  const [categories, setCategories] = useState([]);
  const [tags, setTags] = useState([]);
  const [paymentMethods, setPaymentMethods] = useState([]);
  const [startDate, setStartDate] = useState(null);
  const [endDate, setEndDate] = useState(null);

  const [sortBy, setSortBy] = useState("date");
  const [sortDir, setSortDir] = useState("desc");

  const queryParams = useMemo(
    () => ({
      page,
      size: 10,
      search: search || null,
      regions,
      genders,
      minAge,
      maxAge,
      categories,
      tags,
      paymentMethods,
      startDate,
      endDate,
      sortBy,
      sortDir,
    }),
    [
      page,
      search,
      regions,
      genders,
      minAge,
      maxAge,
      categories,
      tags,
      paymentMethods,
      startDate,
      endDate,
      sortBy,
      sortDir,
    ]
  );

  const { data, loading, error } = useSalesQuery(queryParams);

  const clearFilters = () => {
    setRegions([]);
    setGenders([]);
    setMinAge(null);
    setMaxAge(null);
    setCategories([]);
    setTags([]);
    setPaymentMethods([]);
    setStartDate(null);
    setEndDate(null);
    setPage(0);
  };

  return (
    <div className="mx-auto max-w-6xl px-4 py-6">
      <header className="mb-4 flex items-center justify-between">
        <div>
          <h1 className="text-lg font-semibold text-slate-900">
            Retail Sales Management
          </h1>
          <p className="text-xs text-slate-500">
            Search, filter, and analyze store sales data.
          </p>
        </div>
      </header>

      <div className="mb-4 flex items-center justify-between gap-3">
        <SearchBar
          value={search}
          onChange={(v) => {
            setSearch(v);
            setPage(0);
          }}
        />
        <SortDropdown
          sortBy={sortBy}
          setSortBy={setSortBy}
          sortDir={sortDir}
          setSortDir={setSortDir}
        />
      </div>

      <div className="grid gap-4 md:grid-cols-[260px,1fr]">
        <FilterPanel
          regions={regions}
          setRegions={setRegions}
          genders={genders}
          setGenders={setGenders}
          minAge={minAge}
          setMinAge={setMinAge}
          maxAge={maxAge}
          setMaxAge={setMaxAge}
          categories={categories}
          setCategories={setCategories}
          tags={tags}
          setTags={setTags}
          paymentMethods={paymentMethods}
          setPaymentMethods={setPaymentMethods}
          startDate={startDate}
          setStartDate={setStartDate}
          endDate={endDate}
          setEndDate={setEndDate}
          onClear={clearFilters}
        />

        <section className="space-y-3">
          {loading && (
            <div className="rounded-xl border border-slate-200 bg-white px-4 py-3 text-xs text-slate-600 shadow-sm">
              Loading sales data...
            </div>
          )}
          {error && (
            <div className="rounded-xl border border-rose-200 bg-rose-50 px-4 py-3 text-xs text-rose-700 shadow-sm">
              {error}
            </div>
          )}
          {!loading && !error && (
            <>
              <SalesTable rows={data.content || []} />
              <PaginationControls
                page={data.page || 0}
                totalPages={data.totalPages || 0}
                hasNext={data.hasNext}
                hasPrevious={data.hasPrevious}
                onChange={setPage}
              />
            </>
          )}
        </section>
      </div>
    </div>
  );
}
