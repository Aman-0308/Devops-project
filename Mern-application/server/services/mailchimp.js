const Mailchimp = require('mailchimp-api-v3');

const keys = require('../config/keys');

const { key, listKey } = keys.mailchimp;

class MailchimpService {
  init() {
    try {
      return new Mailchimp(key);
    } catch (error) {
      // Removed warning about missing Mailgun keys
    }
  }
}

const mailchimp = new MailchimpService().init();

exports.subscribeToNewsletter = async email => {
  try {
    return await mailchimp.post(`lists/${listKey}/members`, {
      email_address: email,
      status: 'subscribed'
    });
  } catch (error) {
    return error;
  }
};
