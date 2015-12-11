/* BUTTONS

import java.awt.event.*;
implements MouseMotionListener, MouseListener, Runnable

MouseListener adds 5 methods:
MouseMotionListener adds 2 methods: mouseMoved

IN THE INIT WRITE:
addMouseMotionListener(this);
addMouseListener(this);

The mouse, when clicked, creates a rectangle that can intersect the rectangle of your button.


Must have a run method: public void run()

Go back to Farmworld and copy/paste the wait
Need a public Thread thread;
thread = new Thread(this);
thread.start(); <--- ADD AT THE END OF THE INIT TO START THE RUN METHOD






 */