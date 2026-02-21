
const taskList = [];

// Simulated database delay using a Promise
const saveTask = (task) => {
    return new Promise((resolve) => {
        setTimeout(() => {
            taskList.push(task);
            resolve(`✅ Saved: ${task.title}`);
        }, 500); // 500ms simulation of network/IO delay
    });
};

async function addTask(title, category = "General") {
    console.log(`... Adding Task: "${title}"`);
    const newTask = {
        id: taskList.length + 1,
        title: title,
        category: category,
        completed: false
    };

    // The 'await' keyword is crucial for Day 2 requirements
    const result = await saveTask(newTask);
    console.log(result);
}

// Simulating multiple users interacting with the list
async function runProjectDemo() {
    console.log("=== Collaborative To-Do List (Node.js) ===\n");
    
    // Promise.all simulates concurrent execution
    await Promise.all([
        addTask("Fix Syntax Errors", "Development"),
        addTask("Prepare Presentation", "Admin"),
        addTask("Submit Deliverable 2", "Urgent")
    ]);

    console.log("\n--- Final Task List (Formatted) ---");
    console.table(taskList); 
}

runProjectDemo();
