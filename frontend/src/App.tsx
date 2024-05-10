import React, { useState } from 'react';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import './App.css';
import { Page } from './components/Pages/Page';
import { UserContext } from './contexts/UserContext';
import { ProductDetail } from './components/Product/ProductDetail';
import { Checkout } from './components/Checkout/Checkout';
import Login from './components/Auth/Login';
// import { Logout } from './components/Auth/Logout';
import Register from './components/Auth/Register';
import { AddProduct } from './components/Product/AddProduct';

export const backend = (path?: string) => "http://localhost:8080" + (path ? "/" + path : "");

function App() {
    const [user, setUser] = useState({ username: '', password: '' }); // Initialize user state with empty values

    return (
        <div className="App">
            <header className="App-header"></header>

            <BrowserRouter>
                <UserContext.Provider value={{ user: user, setUser: setUser }}>
                    <Routes>
                        <Route
                            path=""
                            element={
                                <Page>
                                    <h2>Welcome</h2>
                                    <h4>Click here to shop with us!</h4>
                                    <h5>Click here to manage products.</h5>
                                    {/* we should discuss if we want a landing page or just straight to products */}
                                </Page>
                            } 		
                        />
                        <Route 
                            path="/register" 
                            element={<Register />} 
                        />
                        <Route 
                            path="/login" 
                            element={<Login />} 
                        />
                        <Route path="/products" />
                        <Route
                            path="/product/:productId"
                            element={<ProductDetail />}
                        />
                        <Route path="/cart" />
                        <Route 
                            path="/checkout" 
                            element={<Checkout/>}
                        />
                        <Route path="/profile" />

                        {/* Just testing components here...*/}
                        <Route path="/test" element={
                            <AddProduct onSubmit={(p) => console.log(p)}/>
                        }/>
                    </Routes>
                </UserContext.Provider>
            </BrowserRouter>
        </div>
    );
}

export default App;
