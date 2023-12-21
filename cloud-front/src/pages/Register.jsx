import Title from '../components/Title';
import Input from '../components/Input';
import Button from '../components/Button';
import { useState } from 'react';
function Register() {

    
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [mail, setMail] = useState('');
    const [phone, setPhone] = useState('');

    const handleUsernameChange = (e) => {
        setUsername(e.target.value);
    };

    const handlePasswordChange = (e) => {
        setPassword(e.target.value);
    };
    const handleMailChange = (e) => {
        setMail(e.target.value);
    };
    const handlePhoeChange = (e) => {
        setPhone(e.target.value);
    };



    const handleSubmit = async (e) => {
        e.preventDefault();

        if ( !username || !password || !mail || !phone) {
            console.log('Response:', 'Problema con datos vacios');
        }

        const formData = new FormData();
        formData.append('username', username);
        formData.append('password', password);
        formData.append('mail', mail);
        formData.append('phone', phone);

  

        fetch('http://localhost:8080/api/user/register', {
            method: "POST", // or 'PUT'
            body: JSON.stringify(formData), // data can be `string` or {object}!
            headers: {
                "Content-Type": "application/json",
            },
        })
        .then(response => {
            console.log('Response:', response.data);
            // Puedes realizar acciones adicionales después de la carga del archivo
        })
        .catch(error => {
            console.error('Error uploading file:', error);
        });

    };

    return (
        <div className='container'>
        <Title text='Sign Up Now' />
        <form method='POST' onSubmit={handleSubmit}>
            <Input
            name='username'
            type='text'
            text='Username'
            value={username}
            onChange={handleUsernameChange}
            />
            <Input
            name='mail'
            type='mail'
            text='Mail'
            value={mail}

            onChange={handleMailChange}
            />
            <Input
            name='phone'
            type='phone'
            text='Phone'
            value={phone}

            onChange={handlePhoeChange}
            />
            <Input
            name='password'
            type='password'
            text='Password'
            value={password}

            onChange={handlePasswordChange}
            />
            <Button type='submit' text='Continue' />
        </form>
        </div>
    );
}

export default Register;