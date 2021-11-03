import express from 'express'

import { connect } from './src/config/db/mongoDbConfig.js'
import { createInitialData } from './src/config/db/initialData.js'

const app = express()
const env = process.env
const PORT = env.PORT || 8082

connect()
createInitialData()

app.get('/api/status', async (req, res) => {
  let teste = await Order.find()
  console.log(teste)

  return res.status(200).json({
    service: 'Sales-API',
    status: 'up',
    httpStatus: 200
  })
})

app.listen(PORT, () => {
  console.log(`Server started successfully at port ${PORT}`)
})
