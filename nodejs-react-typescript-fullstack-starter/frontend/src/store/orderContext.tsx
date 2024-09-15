import { create } from 'zustand';

const API_ORDENERS = `https://ip:puerto/orders`; 

type Order = {
  id: string;
  description: string;
  quantity: number;
};

type OrderStore = {
  orders: Order[];
  getOrders: () => Promise<void>;
  createOrder: (newOrder: Order) => Promise<void>;
  updateOrder: (updatedOrder: Order) => Promise<void>;
};

const useOrderStore = create<OrderStore>((set) => ({
  orders: [],

  getOrders: async () => {
    try {
      const response = await fetch(API_ORDENERS); 
      const data = await response.json();
      set({ orders: data });
    } catch (error) {
      console.error('Failed to fetch orders:', error);
    }
  },

  createOrder: async (newOrder: Order) => {
    try {
      const response = await fetch(API_ORDENERS, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(newOrder),
      });
      if (!response.ok) throw new Error('Failed to create order');
      const createdOrder = await response.json();
      set((state) => ({
        orders: [...state.orders, createdOrder],
      }));
    } catch (error) {
      console.error('Failed to create order:', error);
    }
  },

  updateOrder: async (updatedOrder: Order) => {
    try {
      const response = await fetch(`${API_ORDENERS}/${updatedOrder.id}`, {
        method: 'PUT',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(updatedOrder),
      });
      if (!response.ok) throw new Error('Failed to update order');
      const updatedOrderFromServer = await response.json();
      set((state) => ({
        orders: state.orders.map((order) =>
          order.id === updatedOrder.id ? updatedOrderFromServer : order
        ),
      }));
    } catch (error) {
      console.error('Failed to update order:', error);
    }
  },
}));

export default useOrderStore;
