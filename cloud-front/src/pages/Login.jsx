import { useState } from 'react'
import './Login.css';

import Link from '../components/Link';
import Title from '../components/Title';

function Login() {


    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');

    const [type, setType] = useState("password");

    const addFocus = e => {
        const inputContainer = e.target.parentElement;
        const label = inputContainer.querySelector('label');
        label.classList.add('focus');

        if (e.onFocus) {
            label.style.color = '';
        }else{
            label.style.color = 'var(--decoration-color)';
        }
    };

    const addBlur = e => {
        const inputContainer = e.target.parentElement;
        const label = inputContainer.querySelector('label');
        label.style.color = 'var(--text-color)';
    }

    const showPass = () => {
        setType(type==='password'?'text':'password');
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        const formData = new FormData();
        formData.append('username', username);
        formData.append('password', password);



        try {
            const response = await fetch('http://localhost:8080/api/user/userlogin', {
                method: 'POST',
                body: formData
            });

            if (!response.ok) {
                throw new Error(`HTTP error! Status: ${response.status}`);
            }

            const data = await response.text();
            console.log('Response:', data);

            setUsername('');
            setPassword('');

            } catch (error) {
                console.error('Error:', error.message);
            }


        };


    return (
        <div className='container'>
            <Title text='Welcome Back'/>
                <form onSubmit={handleSubmit}>
                    <div className='input-container'>
                        <input onChange={(e) => setUsername(e.target.value)} onBlur={addBlur} onFocus={addFocus}  name='username' type='text' value={username} />
                        <label  htmlFor='username' >Username</label>
                    </div>
                    <div className='input-container'>
                        <input onChange={(e) => setPassword(e.target.value)} id='input' onBlur={addBlur} onFocus={addFocus} className="password" name="password" type={type} value={password} />
                        <svg onClick={showPass}  xmlns="http://www.w3.org/2000/svg" height="16" width="18" viewBox="0 0 576 512">
                            <path d="M288 32c-80.8 0-145.5 36.8-192.6 80.6C48.6 156 17.3 208 2.5 243.7c-3.3 7.9-3.3 16.7 0 24.6C17.3 304 48.6 356 95.4 399.4C142.5 443.2 207.2 480 288 480s145.5-36.8 192.6-80.6c46.8-43.5 78.1-95.4 93-131.1c3.3-7.9 3.3-16.7 0-24.6c-14.9-35.7-46.2-87.7-93-131.1C433.5 68.8 368.8 32 288 32zM144 256a144 144 0 1 1 288 0 144 144 0 1 1 -288 0zm144-64c0 35.3-28.7 64-64 64c-7.1 0-13.9-1.2-20.3-3.3c-5.5-1.8-11.9 1.6-11.7 7.4c.3 6.9 1.3 13.8 3.2 20.7c13.7 51.2 66.4 81.6 117.6 67.9s81.6-66.4 67.9-117.6c-11.1-41.5-47.8-69.4-88.6-71.1c-5.8-.2-9.2 6.1-7.4 11.7c2.1 6.4 3.3 13.2 3.3 20.3z" />
                        </svg>
                        <label htmlFor="password">Password</label>
                    </div>

                    <a href="/login">Sign Up</a>
                    <button type='submit'>Register</button>
                </form>
            <footer className='footer'>
                <ul>
                    <li className='border'>
                    <Link link='#' title='Terms of use' msg='Terms of use' />

                    </li>
                    |
                    <li>
                        <Link link='#' title='Terms of use' msg='Privacy Policy' />
                    </li>
                </ul>
            </footer>
        </div>
    );
}

export default Login
