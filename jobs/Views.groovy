listView('ACB_PROJECTS') {
// Adds columns to the views.
    columns {
        status()
        weather()
        name()
        lastSuccess()
        lastFailure()
        lastDuration()
        buildButton()

// Sets a description for the view.
        description('ACB_PROJECTS')
// Adds jobs to the view.
        jobs {
            regex('ACB_.+')
        }
    }
}
