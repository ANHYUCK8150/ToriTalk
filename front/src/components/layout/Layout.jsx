/* eslint-disable max-lines-per-function */
import React from 'react';
import { Routes, Route, useLocation } from 'react-router-dom';
import Header from './Header';
import Footer from './Footer';
import style from './Layout.style';
import HomePage from '../../pages/Home/HomePage';

const Layout = () => {
  const navi = useLocation();
  const { LayoutBox, Section } = style;
  if (navi.pathname !== '/login' && navi.pathname !== '/signup') {
    localStorage.setItem('url', navi.pathname);
  }
  return (
    <LayoutBox>
      <Header />
      <Section className="marginTB">
        <Routes>
          <Route path="/" element={<HomePage />} />
        </Routes>
      </Section>
      <Footer />
    </LayoutBox>
  );
};

export default Layout;
