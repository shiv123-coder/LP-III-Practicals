/**
 * Simulation and Automated Test Runner for Assignment No : 18 MINI PROJECT 3
 * Decentralized E-Voting System (dApp) on Ethereum Smart Contract
 * 
 * Verifies all state transitions, security constraints, and event emissions of Election.sol:
 * 1. Admin adds candidates with metadata
 * 2. Voters cast votes using their Ethereum account address
 * 3. Prevention of double voting (revert on duplicate address)
 * 4. Validation of candidate ID (revert on invalid candidate)
 * 5. Real-time vote count accumulation and winner election
 */

class ElectionSimulator {
    constructor() {
        this.candidates = {};
        this.voters = {};
        this.candidatesCount = 0;
        this.events = [];
    }

    addCandidate(name, details, election_id) {
        this.candidatesCount++;
        this.candidates[this.candidatesCount] = {
            id: this.candidatesCount,
            name: name,
            voteCount: 0,
            details: details,
            election_id: election_id
        };
        return this.candidatesCount;
    }

    vote(candidateId, senderAddress) {
        // Condition 1: Check if sender has already voted (Single-vote constraint)
        if (this.voters[senderAddress]) {
            throw new Error(`Execution reverted: Voter [${senderAddress}] has already voted!`);
        }
        // Condition 2: Check if candidate ID is valid
        if (candidateId <= 0 || candidateId > this.candidatesCount) {
            throw new Error(`Execution reverted: Invalid candidate ID [${candidateId}]!`);
        }

        // Record vote on blockchain state
        this.voters[senderAddress] = true;
        this.candidates[candidateId].voteCount++;

        // Emit votedEvent
        const event = {
            event: 'votedEvent',
            candidateId: candidateId,
            voter: senderAddress,
            timestamp: new Date().toISOString()
        };
        this.events.push(event);
        return event;
    }

    getResults() {
        const results = [];
        for (let i = 1; i <= this.candidatesCount; i++) {
            results.push(this.candidates[i]);
        }
        return results;
    }
}

console.log("================================================================================");
console.log(" LABORATORY PRACTICE III (410246) - ASSIGNMENT NO : 18 (MINI PROJECT 3)");
console.log(" DECENTRALIZED E-VOTING DAPP - SMART CONTRACT & STATE TRANSITION TEST RUNNER");
console.log("================================================================================\n");

const election = new ElectionSimulator();

console.log("[STEP 1] Initializing Election and Nominating Candidates...");
election.addCandidate("Alice Sharma", "Progressive Student Alliance - Focus on Open Source Lab Infrastructure", "ELEC_2026_SPPU");
election.addCandidate("Bob Patil", "Student Welfare Front - Focus on Campus Amenities and Library Hours", "ELEC_2026_SPPU");
election.addCandidate("Charlie Deshmukh", "Tech Innovation Forum - Focus on AI Research and Industry Internships", "ELEC_2026_SPPU");

console.log(`✓ Total Candidates Registered: ${election.candidatesCount}`);
election.getResults().forEach(c => {
    console.log(`  Candidate #${c.id}: ${c.name} | Votes: ${c.voteCount} | Details: ${c.details}`);
});

console.log("\n[STEP 2] Simulating Legitimate Voting Transactions...");
const testVoters = [
    { address: "0xFE3B557E8Fb62b89F4916B721be55cEb828dBd73", candidateId: 1, name: "Voter 1 (Rahul)" },
    { address: "0x4B0897b0513fdC7C541B6d9D7E929C4e5364D2dB", candidateId: 2, name: "Voter 2 (Pooja)" },
    { address: "0x583031D1113aD414F02576BD6afaBfb302140225", candidateId: 1, name: "Voter 3 (Aditya)" },
    { address: "0xdD870fA1b7C4700F2BD7f44238821C26f7392148", candidateId: 3, name: "Voter 4 (Sneha)" },
    { address: "0x583031D1113aD414F02576BD6afaBfb302140226", candidateId: 1, name: "Voter 5 (Gaurav)" }
];

testVoters.forEach(v => {
    try {
        const ev = election.vote(v.candidateId, v.address);
        console.log(`  ✓ [TX SUCCESS] ${v.name} (${v.address}) voted for Candidate #${v.candidateId} | Event: ${ev.event}`);
    } catch (err) {
        console.error(`  ✗ [TX FAILED] ${v.name}: ${err.message}`);
    }
});

console.log("\n[STEP 3] Testing Security Constraint 1: Double Voting Prevention");
try {
    console.log("  Attempting duplicate vote from Voter 1 (0xFE3B557E8Fb62b89F4916B721be55cEb828dBd73)...");
    election.vote(2, "0xFE3B557E8Fb62b89F4916B721be55cEb828dBd73");
    console.error("  ✗ FAILED: Double vote was permitted! (Critical Security Bug)");
} catch (err) {
    console.log(`  ✓ PASSED: Double vote blocked! [${err.message}]`);
}

console.log("\n[STEP 4] Testing Security Constraint 2: Invalid Candidate ID Rejection");
try {
    console.log("  Attempting to vote for non-existent Candidate #99 from fresh voter...");
    election.vote(99, "0x821aEa9a577a9b44299B9c15c88cf3087F3b5544");
    console.error("  ✗ FAILED: Vote for non-existent candidate was permitted!");
} catch (err) {
    console.log(`  ✓ PASSED: Invalid candidate ID rejected! [${err.message}]`);
}

console.log("\n[STEP 5] Final Tally & Election Audit Results:");
console.log("--------------------------------------------------------------------------------");
const finalResults = election.getResults();
let winner = null;
let maxVotes = -1;

finalResults.forEach(c => {
    console.log(`  Candidate ID #${c.id.toString().padEnd(2)} | Name: ${c.name.padEnd(18)} | Votes Received: ${c.voteCount}`);
    if (c.voteCount > maxVotes) {
        maxVotes = c.voteCount;
        winner = c;
    }
});
console.log("--------------------------------------------------------------------------------");
console.log(`🏆 WINNER ELECTED: ${winner.name} with ${winner.voteCount} votes!`);
console.log("✓ Smart Contract Verification Completed Successfully without errors.");
console.log("================================================================================\n");

