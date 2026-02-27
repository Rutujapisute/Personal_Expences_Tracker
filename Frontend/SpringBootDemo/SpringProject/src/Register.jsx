import React, { useState } from 'react';
import { useNavigate, Link } from 'react-router-dom';
import './Register.css';

const Register = () => {
    const navigate = useNavigate();

    const [formData, setFormData] = useState({
        uname: '',        
        email: '',
        password: ''
    });

    const [error, setError] = useState('');
    const [loading, setLoading] = useState(false);

    const handleChange = (e) => {
        setFormData({
            ...formData,
            [e.target.name]: e.target.value
        });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        setError('');
        setLoading(true);

        try {
            const response = await fetch('http://localhost:8080/register', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(formData)
            });

            const message = await response.text();
            console.log('Response:', message);

            if (response.ok) {
                // ✅ FIX: Clear token and set flag
                localStorage.removeItem('token');
                sessionStorage.setItem('justRegistered', 'true');
                
                alert(message); 
                navigate('/login'); 
            } else {
                setError(message || 'Registration failed');
            }

        } catch (err) {
            console.error('Error:', err);
            setError('Server error. Please check if backend is running.');
        } finally {
            setLoading(false);
        }
    };

    return (
        <div className="auth-container">
            <div className="auth-card">
                <h2>Registration</h2>

                {error && <p className="error-msg">{error}</p>}

                <form onSubmit={handleSubmit}>
                    
                    <div className="form-group">
                        <label>Username:</label>
                        <input
                            type="text"
                            name="uname"
                            value={formData.uname}
                            onChange={handleChange}
                            placeholder="Enter username"
                            required
                        />
                    </div>

                    <div className="form-group">
                        <label>Email:</label>
                        <input
                            type="email"
                            name="email"
                            value={formData.email}
                            onChange={handleChange}
                            placeholder="Enter email"
                            required
                        />
                    </div>

                    <div className="form-group">
                        <label>Password:</label>
                        <input
                            type="password"
                            name="password"
                            value={formData.password}
                            onChange={handleChange}
                            placeholder="Enter password"
                            required
                        />
                    </div>

                    <button type="submit" className="auth-btn" disabled={loading}>
                        {loading ? 'Loading...' : 'Register'}
                    </button>
                </form>

                <p className="auth-link">
                    Already have account? <Link to="/login">Login here</Link>
                </p>
            </div>
        </div>
    );
};

export default Register;