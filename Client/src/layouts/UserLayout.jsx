// src/views/UserLayout.jsx
import React from 'react';

import Header from '../components/Header';
import Footer from '../components/Footer';

function UserLayout({ children }) {
  return (
    <div>
      <Header />
      <main>{children}</main>
      <Footer />
    </div>
  );
}

export default UserLayout;
