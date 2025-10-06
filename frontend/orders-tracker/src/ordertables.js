import React, { useState, useEffect } from "react";
import axios from "axios";

// Utility for badge color
const getStatusClass = (status) => {
  switch (status) {
    case "Completed":
      return "bg-green-100 text-green-700";
    case "Cancelled":
      return "bg-red-100 text-red-600";
    case "Restitute":
      return "bg-yellow-100 text-yellow-700";
    case "Continue":
      return "bg-blue-100 text-blue-700";
    case "Hold":
      return "bg-gray-100 text-gray-700";
    case "Delivered":
      return "bg-indigo-100 text-indigo-700";
    default:
      return "bg-gray-100 text-gray-700";
  }
};

const tabs = ["All Orders", "Completed", "Continue", "Restitute", "Cancelled"];
const API_BASE_URL = process.env.REACT_APP_API_URL || "http://spring_backend:8080";

const OrdersTable = () => {
  const [selectedTab, setSelectedTab] = useState("All Orders");
  const [search, setSearch] = useState("");
  const [ordersData, setOrdersData] = useState([]);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);
  const [page, setPage] = useState(0);
  const [size, setSize] = useState(5);
  const [totalPages, setTotalPages] = useState(0);
  

  //Reset page on tab change
  useEffect(() => {
    setPage(0);
  }, [selectedTab]);

  // Fetch paginated orders
  useEffect(() => {
    const fetchOrders = async () => {
      const statusParam = selectedTab === "All Orders" ? "all" : selectedTab;
      setLoading(true);
      setError(null);
      try {
        const response = await axios.get('/api/orders', {
          params: {
            status: statusParam,
            page: page,
            size: size,
          },
        });
        console.log(response.data)
        console.log("Data calling is perfect and data",response.data)        
        setOrdersData(response.data.orders);
        setTotalPages(response.data.totalPages);
      } catch (err) {
        console.error("Error fetching orders:", err);
        setError("Failed to fetch orders.");
      } finally {
        setLoading(false);
      }
    };

    fetchOrders();
  }, [selectedTab, page, size]);

  const filteredOrders = (ordersData || []).filter((order) => {
    const lowerSearch = search.toLowerCase();
    return (
      order.orderName?.toLowerCase().includes(lowerSearch) ||
      order.orderId?.toString().includes(lowerSearch) ||
      order.deliveryStatus?.toLowerCase().includes(lowerSearch) ||
      order.deliveryPricing?.toString().toLowerCase().includes(lowerSearch)
    );
  });


  return (
    <div className="p-6 max-w-5xl mx-auto">
      <h2 className="text-2xl font-bold mb-4 flex items-center space-x-2">
        <span>Orders Details</span>
        <span
          className="text-sm w-5 h-5 flex items-center justify-center rounded-full bg-gray-400 text-white cursor-pointer"
          title="This section shows the list of orders with status, pricing, and delivery info."
        >
          ?
        </span>
      </h2>



      {/* Tabs */}
      {/* Tabs */}
      <div className="flex space-x-6 mb-4 border-b">
        {tabs.map((tab) => (
          <button
            key={tab}
            onClick={() => setSelectedTab(tab)}
            className={`pb-2 font-medium transition-colors duration-200 ${selectedTab === tab
                ? "text-blue-900 border-b-2 border-blue-600"
                : "text-gray-500 hover:text-blue-600"
              }`}
          >
            {tab}
          </button>
        ))}
      </div>

{/* Search + Filter Box */}
<div className="bg-gray-50 p-4 rounded-md mb-4 border border-gray-200">
  <div className="flex items-center space-x-3">
    {/* Search Bar with magnifying glass icon */}
    <div className="relative flex-grow">
      <span className="absolute inset-y-0 left-0 pl-3 flex items-center pointer-events-none">
        <svg
          className="w-5 h-5 text-gray-400"
          fill="none"
          stroke="currentColor"
          strokeWidth="2"
          viewBox="0 0 24 24"
        >
          <path
            strokeLinecap="round"
            strokeLinejoin="round"
            d="M21 21l-4.35-4.35M10.5 18a7.5 7.5 0 1 1 0-15 7.5 7.5 0 0 1 0 15z"
          />
        </svg>
      </span>

      <input
        type="text"
        placeholder="Search for order ID, name, or status"
        value={search}
        onChange={(e) => setSearch(e.target.value)}
        className="w-full pl-10 pr-4 py-2 border border-gray-300 rounded text-sm"
      />
    </div>

    {/* Filter Button */}
    <button className="flex items-center space-x-1 border border-gray-300 text-gray-600 hover:text-black hover:border-gray-400 rounded px-3 py-2 text-sm">
      <svg
        className="w-4 h-4"
        fill="none"
        stroke="currentColor"
        strokeWidth="2"
        viewBox="0 0 24 24"
      >
        <path
          strokeLinecap="round"
          strokeLinejoin="round"
          d="M3 4a1 1 0 011-1h16a1 1 0 011 1v2a1 1 0 01-.293.707L15 13.414V19a1 1 0 01-1.447.894l-4-2A1 1 0 019 17v-3.586L3.293 6.707A1 1 0 013 6V4z"
        />
      </svg>
      <span>Filter</span>
    </button>
  </div>
</div>



      {/* Table */}
      {loading ? (
        <div className="text-center py-4">Loading orders...</div>
      ) : error ? (
        <div className="text-center text-red-600 py-4">{error}</div>
      ) : (
        <div className="overflow-x-auto">
          <table className="w-full table-auto border-collapse">
            <thead>
              <tr className="bg-gray-100 text-left">
                <th className="p-2 border-b">Order ID</th>
                <th className="p-2 border-b">Customer</th>
                <th className="p-2 border-b">Order</th>
                <th className="p-2 border-b">Delivery Date</th>
                <th className="p-2 border-b">Delivery Pricing</th>
                <th className="p-2 border-b">Delivery Status</th>
              </tr>
            </thead>
            <tbody>
              {filteredOrders.length > 0 ? (
                filteredOrders.map((order) => (
                  <tr key={order.orderId} className="hover:bg-gray-50">
                    <td className="p-2 border-none">{order.orderId}</td>
                    <td className="p-2 border-none">{order.customerName}</td>
                    <td className="p-2 border-none">{order.orderName}</td>
                    <td className="p-2 border-none">{order.deliveryDate}</td>
                    <td className="p-2 border-none">{order.deliveryPricing}</td>
                    <td>
                      <span
                        className={`inline-block px-2 py-0.5 text-xs font-medium rounded-md ${getStatusClass(
                          order.deliveryStatus
                        )}`}
                      >
                        {order.deliveryStatus}
                      </span>
                    </td>

                  </tr>
                ))
              ) : (
                <tr>
                  <td colSpan="6" className="text-center p-4">
                    No orders found.
                  </td>
                </tr>
              )}
            </tbody>
          </table>
        </div>
      )}

      <div className="bg-gray-100 px-4 py-2 rounded mt-4">
        <div className="flex justify-between items-center">
          {/* Left: Page info */}
          <span className="text-sm text-gray-700">
            {page + 1} - 5 of {totalPages} pages
          </span>

          {/* Right: Page controls */}
          <div className="flex items-center space-x-3">
            <label className="text-sm text-gray-700">The page you're on</label>

            <select
              value={page}
              onChange={(e) => setPage(parseInt(e.target.value))}
              className="border p-1 rounded"
            >
              {[...Array(totalPages)].map((_, i) => (
                <option key={i} value={i}>
                  {i + 1}
                </option>
              ))}
            </select>

            {/* Previous Button */}
            <button
              onClick={() => setPage((prev) => Math.max(prev - 1, 0))}
              disabled={page === 0}
              className="flex items-center space-x-1 px-3 py-1 border rounded bg-white hover:bg-gray-200 disabled:opacity-50"
            >
              <span>⬅</span>
              <span className="text-sm"></span>
            </button>

            {/* Next Button */}
            <button
              onClick={() => setPage((prev) => Math.min(prev + 1, totalPages - 1))}
              disabled={page === totalPages - 1}
              className="flex items-center space-x-1 px-3 py-1 border rounded bg-white hover:bg-gray-200 disabled:opacity-50"
            >
              <span className="text-sm"></span>
              <span>➡</span>
            </button>
          </div>
        </div>
      </div>



    </div>
  );
};

export default OrdersTable;
