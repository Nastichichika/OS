fun main() {
    RaceConditionSimulator.simulate(DekkerLock())
    RaceConditionSimulator.simulate(DummyLock())
    Bakery.Simulate(BakeryLock(10), 10)

    //Bakery.Simulate(ImprovedBakeryLock(10), 10)
}