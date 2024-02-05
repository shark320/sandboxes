const { Server } = require('mock-socket');

const mockServer = new Server('ws://localhost:10000');

mockServer.on('connection', socket => {
    socket.on('message', data => {
        console.log(`Received: ${data}`);
        // Echo the message back
        socket.send(data);
    });
});

mockServer.

// Don't forget to stop your server when you're done testing
setTimeout(() => {
    mockServer.stop();
}, 30000);