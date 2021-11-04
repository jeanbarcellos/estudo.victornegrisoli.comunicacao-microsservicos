import axios from 'axios'

import { PRODUCT_API_URL } from '../../../config/constants/secrets.js'

class ProductClient {
  async checkProducStock(productsData, token) {
    try {
      const headers = {
        Authorization: token
      }

      console.info(
        `Sending request to Product API with data: ${JSON.stringify(
          productsData
        )}`
      )

      await axios
        .post(
          `${PRODUCT_API_URL}/check-stock`,
          { products: productsData.products },
          { headers }
        )
        .then(res => {
          return true
        })
        .catch(err => {
          return false
        })
    } catch (err) {
      return false
    }
  }
}
export default new ProductClient()
