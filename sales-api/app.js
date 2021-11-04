import express from 'express'

import { connect } from './src/config/db/mongoDbConfig.js'
import { createInitialData } from './src/config/db/initialData.js'
import { connectRabbitMq } from './src/config/rabbitmq/rabbitConfig.js'

import checkToken from './src/config/auth/checkToken.js'
import orderRoutes from './src/modules/sales/routes/OrderRoutes.js'

import { sendMessageToProductStockUpdateQueue } from './src/modules/product/rabbitmq/productStockUpdateSender.js'

const app = express()
const env = process.env
const PORT = env.PORT || 8082

connect()
// createInitialData()
connectRabbitMq()

app.use(express.json())
app.use(checkToken)
app.use(orderRoutes)

app.get('/test', async (req, res) => {
  try {
    sendMessageToProductStockUpdateQueue([
      {
        productId: 1001,
        quantity: 4
      },
      {
        productId: 1002,
        quantity: 2
      },
      {
        productId: 1003,
        quantity: 3
      }
    ])

    return res.status(200).json({ status: 200 })
  } catch (error) {
    console.log(error)
    return res.status(500).json({ error: true })
  }
})

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
