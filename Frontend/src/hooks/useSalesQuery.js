import { useEffect, useState } from "react";
import api from "../services/api";

export function useSalesQuery(queryParams) {
  const [data, setData] = useState({
    content: [],
    page: 0,
    size: 10,
    totalElements: 0,
    totalPages: 0,
    hasNext: false,
    hasPrevious: false,
  });
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState("");

  useEffect(() => {
    setLoading(true);
    setError("");

    api
      .get("/sales", { params: queryParams })
      .then((res) => {
        setData(res.data); // full paginated response
      })
      .catch(() => setError("Failed to load sales data"))
      .finally(() => setLoading(false));
  }, [JSON.stringify(queryParams)]);

  return { data, loading, error };
}
