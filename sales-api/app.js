import express from 'express'

import { connect } from './src/config/db/mongoDbConfig.js'
import { createInitialData } from './src/config/db/initialData.js'
import { connectRabbitMq } from './src/config/rabitmq/rabbitConfig.js'

import checkToken from './src/config/auth/checkToken.js'

const app = express()
const env = process.env
const PORT = env.PORT || 8082

connect()
createInitialData()
connectRabbitMq()

app.use(checkToken)

app.get('/api/status', async (req, res) => {
  return res.status(200).json({
    service: 'Sales-API',
    status: 'up',
    httpStatus: 200
  })
})

app.listen(PORT, () => {
  console.log(`Server started successfully at port ${PORT}`)
})
